package alessandro.stamera.wherewolfserver.controller;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/utenti")  public final class UtenteController
{

    private final UtenteRepository repo;

    public UtenteController(UtenteRepository repo) {
        this.repo = repo;
    }

    @GetMapping public List<Utente> all() {
        return repo.findAll();
    }

    @PostMapping public Utente create(@RequestBody Utente u) {
        return repo.save(u);
    }

    @DeleteMapping("/{username}") public void delete(@PathVariable String username) {
        repo.deleteById(username);
    }

}