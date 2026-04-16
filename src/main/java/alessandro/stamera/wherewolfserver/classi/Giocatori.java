package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;

public class Giocatori
{

    private final Map<String, Ruolo> giocatori;

    public Giocatori() { giocatori = new LinkedHashMap<>(); }

    public void aggiungiGiocatore(String nome, Ruolo ruolo) { giocatori.put(nome, ruolo); }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void eliminaGiocatore(String nome) { }

}