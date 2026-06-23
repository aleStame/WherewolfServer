package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.PROTETTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.MALEDETTO;
import static java.util.Arrays.stream;

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

    public boolean maledizione()
    {
        boolean esito = !protezioni.isNegromantePresente();
        if(esito) aggiungi(MALEDETTO);
        return esito;
    }

    public boolean isPresente(Tratto tratto) { return tratti.contains(tratto); }

    public boolean isProtezionePresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

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

    public boolean isProtezioneLupiPresente() { return protezioni.isProtezioneLupiPresente(); }

    public boolean isProtezioneNegromantePresente() { return protezioni.isNegromantePresente(); }

    public boolean isProtezioneNosferatuPresente() { return protezioni.isNosferatuPresente(); }

    public boolean isProtezionePossedutoPresente() { return protezioni.isPossedutoPresente(); }

    public boolean isProtezioneVampiroPresente() { return false; }

    public void eliminaTratti(Tratto... tratti) { this.tratti.removeAll(stream(tratti).toList()); }

    private void proteggi() { if(!tratti.contains(PROTETTO)) aggiungi(PROTETTO); }

}