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

    private boolean eseguiLogin(String username, String password)
    {
        return repo.findAll().stream().anyMatch(utente -> utente.login(username, password));
    }

    private void inserisciUtente(String username, String password)
    {
        if(getUtente(username).isPresent()) throw new IllegalArgumentException("ERRORE!!! Nome utente già inserito");
        repo.save(new Utente(username, password));
    }

    private Optional<Utente> getUtente(String username) { return repo.findById(username); }

    private void eseguiCambioPassword(String username, String vecchiaPassword, String nuovaPassword)
    {
        Utente utente = getUtente(username).get();
        if(!utente.controlloPassword(vecchiaPassword)) throw new IllegalArgumentException("ERRORE!!! Inserire la password attuale corretta");
        utente.cambiaPassword(nuovaPassword);
    }

}