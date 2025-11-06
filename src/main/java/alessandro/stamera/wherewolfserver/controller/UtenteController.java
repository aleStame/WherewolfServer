package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.classi.Utenti;
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

    private final Utenti utenti;

    public UtenteController(UtenteRepository repo) {  utenti = new Utenti(repo); }

    @GetMapping("/login") public String login(@RequestParam String username, @RequestParam String password)
    {
        String risultato;
        if(utenti.login(username, password)) risultato = "Login eseguito correttamente";
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

    private void inserisciUtente(String username, String password)
    {
        if(isUtentePresente(username)) throw new IllegalArgumentException("ERRORE!!! Nome utente già inserito");
        salvaDatiUtente(username, password);
    }

    private void eseguiCambioPassword(String username, String vecchiaPassword, String nuovaPassword)
    {
        if(controlloPasswordErrata(username, vecchiaPassword)) throw new IllegalArgumentException("ERRORE!!! Inserire la password attuale corretta");
        eseguiCambioPassword(username, nuovaPassword);
        salvaDatiUtente(username, nuovaPassword);
    }

    private void eseguiDisiscrizione(String username, String password)
    {
        if(controlloPasswordErrata(username, password)) throw new IllegalArgumentException("ERRORE!!! Inserire la password corretta");
        eliminaUtente(username);
    }

    private boolean controlloPasswordErrata(String username, String password) { return !cercaUtente(username).controlloPassword(password); }

    private void eseguiCambioPassword(String username, String password) { cercaUtente(username).cambiaPassword(password); }

    private void eliminaUtente(String username) { utenti.eliminaUtente(username); }

    private Utente cercaUtente(String username)
    {
        if(isUtenteAssente(username)) throw new IllegalArgumentException("ERRORE!!! Utente non esistente");
        return getUtente(username).get();
    }

    private boolean isUtentePresente(String username) { return getUtente(username).isPresent(); }

    private boolean isUtenteAssente(String username) { return getUtente(username).isEmpty(); }

    private Optional<Utente> getUtente(String username) { return utenti.getUtente(username); }

    private void salvaDatiUtente(String username, String password) { utenti.salvaDatiUtente(username, password); }

}