package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.MALEDETTO;

public final class Giocatore
{

    private int numeroVoti;

    private final Tratti tratti;

    private boolean amato;

    private Ruolo ruolo;

    public Giocatore(Ruolo ruolo)
    {
        annullaVoti();
        tratti = new Tratti();
        annullaProtezioneAngeloCustode();
        cambiaRuolo(ruolo);
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

    public void protezioneAngeloCustode() { setAmato(true); }

    public void annullaProtezioneAngeloCustode() { setAmato(false); }

    public Ruolo getRuolo() { return ruolo; }

    public boolean isOratore() { return ruolo.isOratore(); }

    public boolean isCitta() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isAngeloCustode() { return false; }

    public boolean isNosferatu() { return false; }

    public void cambiaRuolo(Ruolo ruolo) { this.ruolo = ruolo; }

    private void setNumeroVoti(int numeroVoti) { this.numeroVoti = numeroVoti; }

    private void setAmato(boolean amato) { this.amato = amato; }

}