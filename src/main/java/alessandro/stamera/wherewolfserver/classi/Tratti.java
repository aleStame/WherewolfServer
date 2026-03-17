package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.MALEDETTO;

public final class Tratti
{

    private final List<Tratto> tratti;

    private final Protezioni protezioni;

    public Tratti()
    {
        tratti = new ArrayList<>();
        protezioni = new Protezioni();
    }

    public boolean isMaledetto() { return isPresente(MALEDETTO); }

    public void maledizione() { aggiungi(MALEDETTO); }

    public boolean isPresente(Tratto tratto) { return tratti.contains(tratto); }

    public boolean isProtezionePresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

    public void aggiungiProtezione(Categoria categoria) { protezioni.aggiungiProtezione(categoria); }

    public void aggiungiProtezioneLupi()
    {
        proteggi();
        protezioni.aggiungiProtezioneLupi();
    }

    public void aggiungiProtezioneCreatureOmbra()
    {
        proteggi();
        protezioni.aggiungiProtezioneCreatureOmbra();
    }

    public void aggiungi(Tratto tratto) { tratti.add(tratto); }

    public void aggiungiProtezioneMistici()
    {
        proteggi();
        protezioni.aggiungiProtezioneMistici();
    }

    public void aggiungiProtezione(Ruolo[] ruoli)
    {
        proteggi();
        protezioni.aggiungiProtezione(ruoli);
    }

    public void perdiProtezioni()
    {
        protezioni.perdiProtezioni();
        tratti.remove(PROTETTO);
    }

    private void proteggi() { if(!tratti.contains(PROTETTO)) aggiungi(PROTETTO); }

}