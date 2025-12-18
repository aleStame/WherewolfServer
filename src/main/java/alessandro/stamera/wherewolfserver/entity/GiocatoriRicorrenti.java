package alessandro.stamera.wherewolfserver.entity;

import jakarta.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable public class GiocatoriRicorrenti
{

    private final List<String> giocatori;

    public GiocatoriRicorrenti() { giocatori = new ArrayList<>(); }

    public void aggiungi(String nomeGiocatore)
    {
        if(giocatori.contains(nomeGiocatore)) throw new IllegalArgumentException("ERRORE!!! Utente già inserito");
        giocatori.add(nomeGiocatore);
    }

    public int getNumeroGiocatori() { return giocatori.size(); }

}