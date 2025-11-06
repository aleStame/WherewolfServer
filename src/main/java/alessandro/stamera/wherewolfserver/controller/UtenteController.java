package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.classi.Utenti;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*") @RestController @RequestMapping("/utenti")  public final class UtenteController
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
            utenti.registrazione(username, password);
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
            utenti.cambioPassword(username, vecchiaPassword, nuovaPassword);
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
            utenti.disiscrizione(username, password);
            risultato = "Disiscrizione avvenuta correttamente";
        }
        catch(IllegalArgumentException ex) { risultato = ex.getMessage(); }
        return risultato;
    }

}