package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.AMANTI;

public class Amanti extends Uomini
{

    public Amanti(String nome, String descrizione, String consigli)
    {
        super(nome, AMANTI, BIANCA, descrizione, 2, false, consigli);
    }

    @Override public boolean isAmanti() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = SCONFITTA;
        if(partita.isAngeloCustodePresente() || partita.isGiuliettaPresente())
        {
            esito = NON_FINITO;
            if(partita.getNumeroGiocatoriVivi() == 2) esito = VITTORIA;
        }
        return esito;
    }

}