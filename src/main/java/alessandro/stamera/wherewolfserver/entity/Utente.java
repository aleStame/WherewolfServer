package alessandro.stamera.wherewolfserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity public class Utente
{

    @Id private String username;
    private String password;

    public Utente(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Utente() { }

    public boolean login(String username, String password) { return username.equals(getUsername()) && this.password.equals(password); }

    public String getUsername() { return username; }

    public void cambiaPassword(String password)
    {
        if(this.password.equals(password)) throw new IllegalArgumentException();
        else this.password = password;
    }

}