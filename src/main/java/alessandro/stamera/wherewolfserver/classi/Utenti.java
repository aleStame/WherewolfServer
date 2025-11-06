package alessandro.stamera.wherewolfserver.classi;

import alessandro.stamera.wherewolfserver.repository.UtenteRepository;

public final class Utenti
{

    private final UtenteRepository repo;

    public Utenti(UtenteRepository repo) { this.repo = repo; }

    public boolean login(String username, String password)
    {
        return repo.findAll().stream().anyMatch(utente -> utente.login(username, password));
    }

}