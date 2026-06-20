package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;

public class Villaggio extends Uomini
{

    public Villaggio(String nome, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, VILLAGGIO, aura, descrizione, lune, mistico);
    }

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

    @Override public boolean isVillaggio() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO && partita.isNoCreatureOmbra()) esito = getEsitoPartitaSenzaOmbre(partita);
        return esito;
    }

    @Override public EsitoControlloSensitiva controlloSensitiva() { return EsitoControlloSensitiva.VILLAGGIO; }

    private EsitoPartita getEsitoPartitaSenzaOmbre(Partita partita)
    {
        EsitoPartita esito = getEsitoPartitaCriminali(partita);
        if(isCriminale()) esito = new Criminale(getNome(), getAura(), getDescrizione()).getEsitoPartita(partita);
        return esito;
    }

    private EsitoPartita getEsitoPartitaCriminali(Partita partita)
    {
        EsitoPartita esito = getEsitoPartitaGuardie(partita);
        if(partita.getNumeroCriminali() == 0) esito = VITTORIA;
        return esito;
    }

    private EsitoPartita getEsitoPartitaGuardie(Partita partita)
    {
        EsitoPartita esito = VITTORIA;
        if(partita.isNoGuardie()) esito = SCONFITTA;
        return esito;
    }

}