package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;

public final class GiocatoriEliminati extends Giocatori
{

    @Override public void aggiungiGiocatore(String nome, Ruolo ruolo)
    {
        ruolo.annullaVoti();
        ruolo.resettaRomeo();
        ruolo.resettaAmato();
        ruolo.annullaSegnalazioneAzzeccagarbugli();
        ruolo.eliminaTratto(NON_MORTO);
        if(!ruolo.isContadinoMostro()) ruolo.eliminaTratto(MALEDETTO);
        if(ruolo.isContadinoLupo())
        {
            Tratto[] tratti = new Tratto[] { CREATURA_OMBRA, LUPO_MANNARO };
            for(Tratto tratto : tratti) ruolo.eliminaTratto(tratto);
        }
        ruolo.ripristinaFazioneOriginale();
        ruolo.annullaSegnalazioneInquisitore();
        ruolo.perdiProtezioni();
        super.aggiungiGiocatore(nome, ruolo);
    }

    public boolean isBardoPresente()
    {
        boolean esito = false;
        for(int i = 0; i < getNumeroGiocatori() && !esito; i++) esito = isBardo(i);
        return esito;
    }

    public Aura controlloMedium(String nome) { return getRuolo(nome).controlloMedium(); }

    private boolean isBardo(int posizione) { return getRuolo(posizione).isBardo(); }

    private Ruolo getRuolo(int posizione) { return getRuolo(getNomeGiocatore(posizione)); }

}