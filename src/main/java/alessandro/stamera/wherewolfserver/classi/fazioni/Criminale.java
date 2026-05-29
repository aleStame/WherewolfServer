package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Uomini;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

public class Criminale extends Uomini
{

    private final Potere potere;

    public Criminale(String nome, Aura aura, String descrizione)
    {
        super(nome, CRIMINALI, aura, descrizione, 2, false);
        potere = new Potere();
    }

    @Override public boolean isCriminale() { return true; }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

    @Override public int getNumeroVoti()
    {
        if(isSegnalatoAzzeccagarbugli()) annullaVoti();
        return super.getNumeroVoti();
    }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO) esito = getEsitoPartitaCriminali(partita);
        return esito;
    }

    private EsitoPartita getEsitoPartitaCriminali(Partita partita)
    {
        EsitoPartita esito = SCONFITTA;
        if(partita.isCriminaliPresenti()) esito = getEsitoPartitaGuardie(partita);
        return esito;
    }

    private EsitoPartita getEsitoPartitaGuardie(Partita partita)
    {
        EsitoPartita esito = SCONFITTA;
        if(partita.isNoGuardie()) esito = VITTORIA;
        return esito;
    }

}