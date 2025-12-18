package alessandro.stamera.wherewolfserver.entity;

import jakarta.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;
import static java.text.Collator.getInstance;
import static java.util.Locale.ITALY;

@Embeddable public class GiocatoriRicorrenti
{

    private final List<String> giocatori;

    public GiocatoriRicorrenti() { giocatori = new ArrayList<>(); }

    public void aggiungi(String nomeGiocatore)
    {
        if(cercaGiocatore(nomeGiocatore)) throw new IllegalArgumentException("ERRORE!!! Utente già inserito");
        giocatori.add(nomeGiocatore);
        giocatori.sort(getInstance(ITALY));
    }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void elimina(String nomeGiocatore) { giocatori.remove(nomeGiocatore); }

    public String getNomeGiocatore(int posizione) { return giocatori.get(posizione); }

    private boolean cercaGiocatore(String nomeGiocatore) { return giocatori.contains(nomeGiocatore); }

}