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
        setPassword(password);
    }

    public Utente() { }

    public boolean login(String username, String password) { return username.equals(getUsername()) && controllaPassword(password); }

    public String getUsername() { return username; }

    public void cambiaPassword(String password)
    {
        if(controllaPassword(password)) throw new IllegalArgumentException("ERRORE!!! Password uguale alla precedente");
        else setPassword(password);
    }

    private void setPassword(String password) { this.password = password; }

    private boolean controllaPassword(String password) { return this.password.equals(password); }

}