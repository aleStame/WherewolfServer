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
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;

public final class Giocatore
{

    private int numeroVoti;

    private final Tratti tratti;

    private boolean amato, segnalatoAzzeccagarbugli, segnalatoInquisitore, romeo;

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
        setRomeo(false);
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
        if(isGiocatoreAuraNera()) aura = NERA;
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
        EsitoAttacco esito = ruolo.attaccoNosferatu();
        if(esito == RIUSCITO) nosferatizzazione();
        return esito;
    }

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
        setFazione(CRIMINALI);
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
        EsitoAttacco esito = ruolo.attaccoLupi(lupo);
        if(isProtezioneAngeloCustodeNonBucata()) esito = gestioneAttaccoVittimaProtetta(lupo, esito);
        esito = verificaEsitoAttaccoLupi(lupo, esito);
        return esito;
    }

    private EsitoAttacco gestioneAttaccoVittimaProtetta(Ruolo lupo, EsitoAttacco esito)
    {
        if(isAmato()) esito = ANGELO_CUSTODE_MORTO;
        else if(isGiocatoreProtetto(lupo)) esito = FALLITO;
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
            case VAMPIRO, NEGROMANTE -> esito = FALLITO;
        }
        return esito;
    }

    public void riconosciNegromante() { cambiaFazione(NEGROMANTE); }

    public EsitoAttacco vampirizzazione()
    {
        EsitoAttacco esito = ruolo.vampirizzazione();
        if(isGiocatoreLupizzato()) esito = MORTO;
        if(esito == RIUSCITO) trasformaVampiro();
        return esito;
    }

    public EsitoAttacco passaPosseduto()
    {
        EsitoAttacco esito = ruolo.passaPosseduto();
        if(isNonMorto()) esito = RIUSCITO;
        return esito;
    }

    public boolean isNonMorto() { return tratti.isPresente(NON_MORTO); }

    public void romeizzazione() { setRomeo(true); }

    public boolean isRomeo() { return romeo; }

    public boolean isContadino() { return ruolo.isContadino(); }

    public boolean isContadinoEroe() { return ruolo.isContadinoEroe(); }

    public boolean isContadinoLupo() { return ruolo.isContadinoLupo(); }

    public boolean isMegera() { return true; }

    private void trasformaVampiro()
    {
        trasformaNonMorto();
        setFazione(VAMPIRO);
    }

    private void nosferatizzazione()
    {
        setFazione(NOSFERATU);
        trasformaNonMorto();
    }

    private void setFazione(Fazione fazione) { this.fazione = fazione; }

    private void trasformaNonMorto() { tratti.aggiungi(NON_MORTO); }

    private boolean isGiocatoreAuraNera() { return isMaledetto() || isGiocatoreLupizzato(); }

    private void setRomeo(boolean romeo) { this.romeo = romeo; }

    private boolean isProtezioneAngeloCustodeNonBucata()
    {
        return !ruolo.isContadinoLupo() && !isCappuccettoRosso() && isAmato();
    }

    private boolean isGiocatoreLupizzato() { return isFazione(LUPO_BRANCO) || isFazione(LUPO_SOLITARIO); }

    private boolean isFazione(Fazione fazione) { return this.fazione == fazione; }

    private EsitoAttacco verificaEsitoAttaccoLupi(Ruolo lupo, EsitoAttacco esito)
    {
        switch(esito)
        {
            case CONTADINO_LUPO_BECCATO -> cambiaFazione(lupo.getFazione());
            case FALLITO -> { if(lupo.isLupoSolitario()) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO; }
            case ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO -> { if(isProtetto(lupo)) esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO; }
        }
        return esito;
    }

    private boolean isProtetto(Ruolo lupo) { return isAmato() || isProtezionePresente(lupo); }

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

    public EsitoAttacco attaccoNegromante()
    {
        EsitoAttacco esito = ruolo.attaccoNegromante();
        if(esito == RIUSCITO) maledizione();
        return esito;
    }

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