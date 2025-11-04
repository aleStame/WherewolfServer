package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.springframework.web.bind.annotation.*;

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
        if(isUtentePresente(username)) risultato = "ERRORE!!! Nome utente già inserito";
        else
        {
            inserisciUtente(username, password);
            risultato = "Registrazione avvenuta correttamente";
        }
        return risultato;
    }

    @PostMapping("/cambioPassword")
    public String cambioPassword(@RequestParam String username, @RequestParam String vecchiaPassword, @RequestParam String nuovaPassword)
    {
        String risultato;
        try
        {
            repo.findById(username).get().cambiaPassword(nuovaPassword);
            risultato = "Password cambiata correttamente";
        }
        catch(IllegalArgumentException ex) { risultato = ex.getMessage(); }
        return risultato;
    }

    private boolean eseguiLogin(String username, String password)
    {
        return repo.findAll().stream().anyMatch(utente -> utente.login(username, password));
    }

    private boolean isUtentePresente(String username) { return repo.findById(username).isPresent(); }

    private void inserisciUtente(String username, String password) { repo.save(new Utente(username, password)); }

}