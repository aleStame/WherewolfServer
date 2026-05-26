package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

public class Villaggio extends Ruolo
{

    public Villaggio(String nome, Aura aura, String descrizione, int lune, boolean mistico)
    {
        super(nome, VILLAGGIO, aura, descrizione, lune, mistico);
    }

    @Override public EsitoAttacco gildata()
    {
        cambiaFazione(CRIMINALI);
        return RIUSCITO;
    }

    @Override public boolean isVillaggio() { return true; }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == VITTORIA && partita.getNumeroCriminali() > 0) esito = getEsitoPartitaGuardie(partita);
        return esito;
    }

    @Override public EsitoControlloSensitiva controlloSensitiva() { return EsitoControlloSensitiva.VILLAGGIO; }

    private EsitoPartita getEsitoPartitaGuardie(Partita partita)
    {
        EsitoPartita esito = VITTORIA;
        if(partita.isNoGuardie()) esito = SCONFITTA;
        return esito;
    }

}