package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CITTA;

public class Citta extends Uomini
{

    public Citta(String nome, Aura aura, String descrizione, String consigli)
    {
        super(nome, CITTA, aura, descrizione, 2, false, consigli);
    }

    @Override public boolean isCitta() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO && partita.isNoCreatureOmbra()) esito = getEsitoPartitaSenzaOmbra(partita);
        return esito;
    }

    private EsitoPartita getEsitoPartitaSenzaOmbra(Partita partita)
    {
        EsitoPartita esito = VITTORIA;
        if(isCriminale()) esito = new Criminale(getNome(), controlloVeggente(), getDescrizione(), null).getEsitoPartita(partita);
        return esito;
    }

}