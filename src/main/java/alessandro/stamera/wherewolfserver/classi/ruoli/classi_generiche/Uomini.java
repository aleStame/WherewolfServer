package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;

public class Uomini extends Ruolo
{

    public Uomini(String nome, Fazione fazione, Aura aura, String descrizione, int lune, boolean mistico, String consigli)
    {
        super(nome, fazione, aura, descrizione, lune, mistico, UOMINI, consigli);
    }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO && (partita.getNumeroSenzaFazioneVivi() + partita.getNumeroCreatureOmbraVive() == partita.getNumeroGiocatoriVivi())) esito = SCONFITTA;
        return esito;
    }

}