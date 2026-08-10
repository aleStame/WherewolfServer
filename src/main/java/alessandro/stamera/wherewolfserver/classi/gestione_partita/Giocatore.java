package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.MALEDETTO;

public final class Giocatore
{

    private int numeroVoti;

    private final Tratti tratti;

    private boolean amato, segnalatoAzzeccagarbugli, segnalatoInquisitore;

    private Ruolo ruolo;

    private Fazione fazione;

    public Giocatore(Ruolo ruolo)
    {
        annullaVoti();
        tratti = new Tratti();
        annullaProtezioneAngeloCustode();
        cambiaRuolo(ruolo);
        annullaSegnalazioneAzzeccagarbugli();
        annullaSegnalazioneInquisitore();
        cambiaFazione(NESSUNA);
    }

    public void incrementaVoti(int numeroVoti) { setNumeroVoti(getNumeroVoti() + numeroVoti); }

    public int getNumeroVoti()
    {
        int risultato = numeroVoti;
        if(isMaledetto()) risultato++;
        if(isSegnalatoAzzeccagarbugli() && !isAccusabileAzzeccagarbugli()) risultato = 0;
        return risultato;
    }

    public void annullaVoti() { setNumeroVoti(0); }

    public void maledizione() { tratti.aggiungi(MALEDETTO); }

    public Aura getAura()
    {
        Aura aura = ruolo.getAura();
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

    public EsitoAttacco progenizzazioneNosferatu() { return ruolo.attaccoNosferatu(); }

    public EsitoAttacco attaccoAssassino()
    {
        EsitoAttacco esito = ruolo.attaccoAssassino();
        if(isAmato()) esito = ANGELO_CUSTODE_MORTO;
        return esito;
    }

    public void perdiProtezioni() { tratti.perdiProtezioni(); }

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
        System.out.println(risultato);
        return risultato;
    }

    public boolean isCriminale() { return getFazione() == CRIMINALI; }

    public boolean isGuardia() { return ruolo.isGuardia(); }

    public boolean isLupo() { return ruolo.isLupo(); }

    public boolean isBracconiere() { return ruolo.isBracconiere(); }

    public void segnalazioneAzzeccagarbugli() { setSegnalatoAzzeccagarbugli(true); }

    public boolean isAccusabile() { return isAccusabileAzzeccagarbugli() || isAccusabileInquisizione(); }

    public void annullaSegnalazioneInquisitore() { setSegnalazioneInquisitore(false); }

    public void annullaSegnalazioneAzzeccagarbugli() { setSegnalatoAzzeccagarbugli(false); }

    public boolean isCacciatore() { return ruolo.isCacciatore(); }

    public boolean isLupoSolitario() { return ruolo.isLupoSolitario(); }

    public boolean isNegromante() { return ruolo.isNegromante(); }

    public boolean isCapoGilda() { return ruolo.isCapoGilda(); }

    public boolean isNonna() { return ruolo.isNonna(); }

    public boolean isCappuccettoRosso() { return ruolo.isCappuccettoRosso(); }

    public void aggiungiProtezione(Ruolo ruolo) { tratti.aggiungiProtezione(ruolo); }

    public boolean isProtezionePresente(Ruolo attaccante)
    {
        return isGiocatoreProtetto(attaccante) || ruolo.isProtezionePresente(attaccante);
    }

    public EsitoAttacco attaccoLupi(Ruolo lupo)
    {
        EsitoAttacco esito = FALLITO;
        if(isAmato() && !ruolo.isContadinoLupo()) esito = ANGELO_CUSTODE_MORTO;
        else if(!isGiocatoreProtetto(lupo)) esito = ruolo.attaccoLupi(lupo);
        esito = verificaEsitoAttaccoLupi(lupo, esito);
        return esito;
    }

    public boolean isGuaritore() { return ruolo.isGuaritore(); }

    public void annullaMaledizione() { tratti.eliminaTratti(MALEDETTO); }

    public EsitoAttacco gildata()
    {
        EsitoAttacco esito = ruolo.gildata();
        switch(fazione)
        {
            case LUPO_BRANCO, LUPO_SOLITARIO -> esito = MORTO;
            case NEGROMANTE -> esito = FALLITO;
        }
        return esito;
    }

    public void riconosciNegromante() { cambiaFazione(NEGROMANTE); }

    public EsitoAttacco vampirizzazione()
    {
        EsitoAttacco esito = ruolo.vampirizzazione();
        if(isGiocatoreLupizzato()) esito = MORTO;
        return esito;
    }

    private boolean isGiocatoreLupizzato() { return fazione == LUPO_BRANCO || fazione == LUPO_SOLITARIO; }

    private EsitoAttacco verificaEsitoAttaccoLupi(Ruolo lupo, EsitoAttacco esito)
    {
        switch(esito)
        {
            case CONTADINO_LUPO_BECCATO -> cambiaFazione(lupo.getFazione());
            case FALLITO -> { if(lupo.isLupoSolitario()) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO; }
            case ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO -> { if(isAmato()) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO; }
        }
        return esito;
    }

    private void cambiaFazione(Fazione fazione) { this.fazione = fazione; }

    private boolean isGiocatoreProtetto(Ruolo lupo) { return tratti.isProtezionePresente(lupo); }

    private boolean isAccusabileInquisizione() { return isInquisito() && ruolo.isMistico(); }

    private boolean isAccusabileAzzeccagarbugli()
    {
        Fazione fazione = getFazione();
        return fazione != CITTA && fazione != CRIMINALI && isSegnalatoAzzeccagarbugli();
    }

    public boolean isSegnalatoAzzeccagarbugli() { return segnalatoAzzeccagarbugli; }

    public boolean isInquisito() { return segnalatoInquisitore; }

    private void setSegnalazioneInquisitore(boolean segnalatoInquisitore)
    {
        this.segnalatoInquisitore = segnalatoInquisitore;
    }

    private void setNumeroVoti(int numeroVoti) { this.numeroVoti = numeroVoti; }

    private void setAmato(boolean amato) { this.amato = amato; }

    private void setSegnalatoAzzeccagarbugli(boolean segnalatoAzzeccagarbugli)
    {
        this.segnalatoAzzeccagarbugli = segnalatoAzzeccagarbugli;
    }

}