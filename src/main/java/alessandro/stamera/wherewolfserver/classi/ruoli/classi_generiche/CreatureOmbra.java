package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;

public class CreatureOmbra extends Ruolo
{

    public CreatureOmbra
    (
        String nome, Fazione fazione, Aura aura, String descrizione, int lune, boolean mistico, String consigli
    )
    {
        super(nome, fazione, aura, descrizione, lune, mistico, CREATURE_OMBRA, consigli);
    }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == VITTORIA)
        {
            if(partita.isNoCreatureOmbra()) esito = SCONFITTA;
            else if(partita.isNoUomini()) esito = VITTORIA;
            else esito = NON_FINITO;
        }
        return esito;
    }

    @Override public EsitoAttacco gildata() { return FALLITO; }

}