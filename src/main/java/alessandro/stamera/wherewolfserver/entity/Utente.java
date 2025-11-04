package alessandro.stamera.wherewolfserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity public class Utente
{

    @Id private final String username;
    private final String password;

    public Utente(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) { return this.username.equals(username) && this.password.equals(password); }

    public String getUsername() { return ""; }

}