package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;

public class Villaggio extends Uomini
{

    public Villaggio(String nome, Aura aura, String descrizione, int lune, boolean mistico, String consigli)
    {
        super(nome, VILLAGGIO, aura, descrizione, lune, mistico, consigli);
    }

    @Override public boolean isVillaggio() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == VITTORIA && partita.isNoCreatureOmbra()) esito = getEsitoPartitaSenzaOmbre(partita);
        return esito;
    }

    @Override public EsitoControlloSensitiva controlloSensitiva() { return EsitoControlloSensitiva.VILLAGGIO; }

    private EsitoPartita getEsitoPartitaSenzaOmbre(Partita partita)
    {
        EsitoPartita esito = getEsitoPartitaCriminali(partita);
        if(isCriminale()) esito = new Criminale(getNome(), controlloVeggente(), getDescrizione(), null).getEsitoPartita(partita);
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