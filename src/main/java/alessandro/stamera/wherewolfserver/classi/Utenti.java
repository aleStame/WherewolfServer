package alessandro.stamera.wherewolfserver.classi;

import alessandro.stamera.wherewolfserver.entity.Utente;
import alessandro.stamera.wherewolfserver.repository.UtenteRepository;

import java.util.Optional;

public final class Utenti
{

    private final UtenteRepository repo;

    public Utenti(UtenteRepository repo) { this.repo = repo; }

    public boolean login(String username, String password)
    {
        return repo.findAll().stream().anyMatch(utente -> utente.login(username, password));
    }

    public void registrazione(String username, String password) { throw new IllegalArgumentException(); }

    public void eliminaUtente(String username) { repo.delete(cercaUtente(username)); }

    public Optional<Utente> getUtente(String username) { return repo.findById(username); }

    public void salvaDatiUtente(String username, String password) { repo.save(new Utente(username, password)); }

    private Utente cercaUtente(String username)
    {
        if(isUtenteAssente(username)) throw new IllegalArgumentException("ERRORE!!! Utente non esistente");
        return getUtente(username).get();
    }

    private boolean isUtenteAssente(String username) { return getUtente(username).isEmpty(); }

}