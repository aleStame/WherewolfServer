package alessandro.stamera.wherewolfserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity public class Utente
{

    @Id private String username;
    private String password;

    public Utente() {}

    public Utente(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) { return true; }

}