package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NESSUNA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.MALEDETTO;

public final class Giocatore
{

    private int numeroVoti;

    private final Tratti tratti;

    private boolean amato, segnalatoInquisitore;

    private Ruolo ruolo;

    private Fazione fazione;

    public Giocatore(Ruolo ruolo)
    {
        annullaVoti();
        tratti = new Tratti();
        annullaProtezioneAngeloCustode();
        cambiaRuolo(ruolo);
        setSegnalazioneInquisitore(false);
        fazione = NESSUNA;
    }

    public void incrementaVoti(int numeroVoti) { setNumeroVoti(getNumeroVoti() + numeroVoti); }

    public int getNumeroVoti()
    {
        int risultato = numeroVoti;
        if(isMaledetto()) risultato++;
        return risultato;
    }

    public void annullaVoti() { setNumeroVoti(0); }

    public void maledizione() { tratti.aggiungi(MALEDETTO); }

    public Aura getAura()
    {
        Aura aura = BIANCA;
        if(isMaledetto()) aura = NERA;
        return aura;
    }

    public boolean isMaledetto() { return tratti.isMaledetto(); }

    public boolean isAmato() { return amato; }

    public void protezioneAngeloCustode() { setAmato(true); }

    public void annullaProtezioneAngeloCustode() { setAmato(false); }

    public Ruolo getRuolo() { return ruolo; }

    public boolean isOratore() { return ruolo.isOratore(); }

    public boolean isCitta() { return ruolo.isCitta(); }

    public boolean isContadinoMostro() { return ruolo.isContadinoMostro(); }

    public boolean isAngeloCustode() { return ruolo.isAngeloCustode(); }

    public boolean isNosferatu() { return ruolo.isNosferatu(); }

    public void cambiaRuolo(Ruolo ruolo) { this.ruolo = ruolo; }

    public EsitoAttacco progenizzazioneNosferatu()
    {
        EsitoAttacco esito = RIUSCITO;
        if(isNosferatuMorto()) esito = MORTO;
        else if(ruolo.isPosseduto()) esito = TROVATO_POSSEDUTO;
        else if(ruolo.isProtezioneNosferatuPresente()) esito = FALLITO;
        return esito;
    }

    public EsitoAttacco attaccoAssassino()
    {
        EsitoAttacco esito = RIUSCITO;
        if(isAmato()) esito = ANGELO_CUSTODE_MORTO;
        return esito;
    }

    public void perdiProtezioni() { tratti.perdiProtezioni(); }

    public boolean isInquisito() { return segnalatoInquisitore; }

    public void segnalazioneInquisitore() { if(ruolo.isMistico()) setSegnalazioneInquisitore(true); }

    public EsitoAttacco criminalizzazione()
    {
        fazione = CRIMINALI;
        return RIUSCITO;
    }

    public Fazione getFazione()
    {
        Fazione risultato = fazione;
        if(risultato == NESSUNA) risultato = ruolo.getFazione();
        return risultato;
    }

    public boolean isCriminale() { return getFazione() == CRIMINALI; }

    public boolean isGuardia() { return ruolo.isGuardia(); }

    private void setSegnalazioneInquisitore(boolean segnalatoInquisitore)
    {
        this.segnalatoInquisitore = segnalatoInquisitore;
    }

    private boolean isNosferatuMorto() { return ruolo.isCacciatoreDiVampiri() || ruolo.isLupo() || isContadinoMostro(); }

    private void setNumeroVoti(int numeroVoti) { this.numeroVoti = numeroVoti; }

    private void setAmato(boolean amato) { this.amato = amato; }

}