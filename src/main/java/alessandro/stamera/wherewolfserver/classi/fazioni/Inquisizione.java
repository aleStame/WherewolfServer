package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.INQUISIZIONE;

public class Inquisizione extends Uomini
{

    public Inquisizione(String nome, Aura aura, String descrizione)
    {
        super(nome, INQUISIZIONE, aura, descrizione, 3, false);
    }

    @Override public boolean isInquisizione() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO && partita.isNoCreatureOmbra())
        {
            if(partita.isMisticiPresenti()) esito = SCONFITTA;
            else esito = VITTORIA;
        }
        return esito;
    }

}