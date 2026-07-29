package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratti;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.MALEDETTO;

public final class Giocatore
{

    private int numeroVoti;

    private final Tratti tratti;

    private boolean amato;

    public Giocatore()
    {
        annullaVoti();
        tratti = new Tratti();
        annullaProtezioneAngeloCustode();
    }

    public void incrementaVoti(int numeroVoti) { setNumeroVoti(getNumeroVoti() + numeroVoti); }

    public int getNumeroVoti()
    {
        int risultato = numeroVoti;
        if(isMaledetto()) risultato++;
        return risultato;
    }

    public void annullaVoti() { setNumeroVoti(0); }

    public void maledizione() { tratti.aggiungi(MALEDETTO); }

    public Aura getAura()
    {
        Aura aura = BIANCA;
        if(isMaledetto()) aura = NERA;
        return aura;
    }

    public boolean isMaledetto() { return tratti.isMaledetto(); }

    public boolean isAmato() { return amato; }

    public void protezioneAngeloCustode() { amato = true; }

    public void annullaProtezioneAngeloCustode() { amato = false; }

    private void setNumeroVoti(int numeroVoti) { this.numeroVoti = numeroVoti; }

}