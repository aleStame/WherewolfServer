package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@RestController @RequestMapping("/utenti")  public final class UtenteController
{

    private final UtenteRepository repo;

    public UtenteController(UtenteRepository repo) {  this.repo = repo; }

    @GetMapping("/login") public String login(@RequestParam String username, @RequestParam String password)
    {
        String risultato;
        if(eseguiLogin(username, password)) risultato = "Login eseguito correttamente";
        else risultato = "ERRORE!!! Username o password errate";
        return risultato;
    }

    @PostMapping("/registrazione") public String registrazione(@RequestParam String username, @RequestParam String password)
    {
        String risultato;
        try
        {
            inserisciUtente(username, password);
            risultato = "Registrazione avvenuta correttamente";
        }
        catch(IllegalArgumentException ex) { risultato = ex.getMessage(); }
        return risultato;
    }

    @PostMapping("/cambioPassword")
    public String cambioPassword(@RequestParam String username, @RequestParam String vecchiaPassword, @RequestParam String nuovaPassword)
    {
        String risultato;
        try
        {
            eseguiCambioPassword(username, vecchiaPassword, nuovaPassword);
            risultato = "Password cambiata correttamente";
        }
        catch(IllegalArgumentException ex) { risultato = ex.getMessage(); }
        return risultato;
    }

    @PostMapping("/disiscrizione") public String disiscrizione(@RequestParam String username, @RequestParam String password)
    {
        String risultato;
        try
        {
            eseguiDisiscrizione(username, password);
            risultato = "Disiscrizione avvenuta correttamente";
        }
        catch(IllegalArgumentException ex) { risultato = ex.getMessage(); }
        return risultato;
    }

    private boolean eseguiLogin(String username, String password)
    {
        return repo.findAll().stream().anyMatch(utente -> utente.login(username, password));
    }

    private void inserisciUtente(String username, String password)
    {
        if(repo.findById(username).isPresent()) throw new IllegalArgumentException("ERRORE!!! Nome utente già inserito");
        repo.save(new Utente(username, password));
    }

    private void eseguiCambioPassword(String username, String vecchiaPassword, String nuovaPassword)
    {
        Utente utente = getUtente(username);
        if(!utente.controlloPassword(vecchiaPassword)) throw new IllegalArgumentException("ERRORE!!! Inserire la password attuale corretta");
        utente.cambiaPassword(nuovaPassword);
        repo.save(utente);
    }

    private void eseguiDisiscrizione(String username, String password)
    {
        Utente utente = getUtente(username);
        if(!utente.controlloPassword(password)) throw new IllegalArgumentException("ERRORE!!! Inserire la password corretta");
        repo.delete(getUtente(username));
    }

    private Utente getUtente(String username)
    {
        Optional<Utente> risultato = repo.findById(username);
        if(risultato.isEmpty()) throw new IllegalArgumentException("ERRORE!!! Utente non esistente");
        return risultato.get();
    }

}