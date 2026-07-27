package alessandro.stamera.wherewolfserver.classi.fazioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;

public class Lupo extends CreatureOmbra
{

    public Lupo(String nome, String descrizione, int lune)
    {
        super(nome, LUPO_BRANCO, NERA, descrizione, lune, false);
        aggiungiTratti(CREATURA_OMBRA, LUPO_MANNARO);
    }

    @Override public boolean isLupo() { return true; }

    @Override public EsitoAttacco attaccoNosferatu() { return getMorto(); }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco esito = FALLITO;
        if(ruolo == this && !isProtetto()) esito = RIUSCITO;
        return esito;
    }

    @Override public EsitoAttacco gildata() { return getMorto(); }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito == NON_FINITO && isPresentiSoloLupiConSenzaFazione(partita)) esito = VITTORIA;
        return esito;
    }

    @Override public EsitoAttacco vampirizzazione() { return MORTO; }

    private boolean isPresentiSoloLupiConSenzaFazione(Partita partita)
    {
        return partita.getNumeroGiocatoriVivi() == getNumeroLupiConSenzaFazione(partita);
    }

    private int getNumeroLupiConSenzaFazione(Partita partita)
    {
        return partita.getNumeroLupiBrancoVivi() + partita.getNumeroSenzaFazioneVivi();
    }

    private boolean isProtetto() { return isRomeo() || isAmato(); }

    private EsitoAttacco getMorto() { return MORTO; }

}
