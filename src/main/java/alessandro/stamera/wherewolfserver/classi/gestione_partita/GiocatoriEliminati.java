package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public final class GiocatoriEliminati extends Giocatori
{

    public boolean isBardoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = isBardo(i);
        return esito;
    }

    public Aura controlloMedium(String nome) { return null; }

    private boolean isBardo(int posizione) { return getRuolo(posizione).isBardo(); }

    private Ruolo getRuolo(int posizione) { return getRuolo(getNomeGiocatore(posizione)); }

}