package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;

public class Citta extends Uomini
{

    public Citta(String nome, Aura aura, String descrizione) { super(nome, CITTA, aura, descrizione, 2, false); }

    @Override public boolean isCitta() { return true; }

    @Override public EsitoAttacco gildata()
    {
        EsitoAttacco esito = super.gildata();
        if(!isTrattoPresente(NON_MORTO))
        {
            cambiaFazione(CRIMINALI);
            esito = RIUSCITO;
        }
        return esito;
    }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO && partita.isNoCreatureOmbra()) esito = getEsitoPartitaSenzaOmbra(partita);
        return esito;
    }

    @Override public void incrementaVoti(int numeroVoti)
    {
        int voti = numeroVoti;
        if(isSegnalatoAzzeccagarbugli()) voti = 0;
        super.incrementaVoti(voti);
    }

    private EsitoPartita getEsitoPartitaSenzaOmbra(Partita partita)
    {
        EsitoPartita esito = VITTORIA;
        if(isCriminale()) esito = new Criminale(getNome(), getAura(), getDescrizione()).getEsitoPartita(partita);
        return esito;
    }

}