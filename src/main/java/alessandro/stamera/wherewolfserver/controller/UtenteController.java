package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/utenti")  public final class UtenteController
{

    private final UtenteRepository repo;

    public UtenteController(UtenteRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login") public String login(@RequestParam String username, @RequestParam String password)
    {
        String risultato;
        if(repo.findAll().stream().anyMatch(utente -> utente.login(username, password))) risultato = "Login eseguito correttamente";
        else risultato = "ERRORE!!! Username o password errate";
        return risultato;
    }

}