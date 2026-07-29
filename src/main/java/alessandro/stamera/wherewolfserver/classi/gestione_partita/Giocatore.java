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

    public Giocatore()
    {
        annullaVoti();
        tratti = new Tratti();
    }

    public void incrementaVoti(int numeroVoti) { this.numeroVoti += numeroVoti; }

    public int getNumeroVoti()
    {
        int risultato = numeroVoti;
        if(isMaledetto()) risultato++;
        return risultato;
    }

    public void annullaVoti() { numeroVoti = 0; }

    public void maledizione() { tratti.aggiungi(MALEDETTO); }

    public Aura getAura()
    {
        Aura aura = BIANCA;
        if(isMaledetto()) aura = NERA;
        return aura;
    }

    public boolean isMaledetto() { return tratti.isMaledetto(); }

}