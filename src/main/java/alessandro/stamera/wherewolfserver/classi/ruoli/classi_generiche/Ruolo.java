package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.*;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.NON_VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.MALEDETTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.NON_MORTO;

public class Ruolo
{

    private final String nome, descrizione;

    private Fazione fazione;

    private final Fazione fazioneOriginale;

    private final Aura aura;

    private final int lune;

    private int voti;

    private boolean amato, romeo, segnalazioneAzzeccagarbugli, inquisito, segnalatoBoia, segnalatoOratore;

    private final boolean mistico;

    private final Tratti tratti;

    public Ruolo(String nome, Fazione fazione, Aura aura, String descrizione, int lune, boolean mistico)
    {
        this.nome = nome;
        cambiaFazione(fazione);
        this.aura = aura;
        this.descrizione = descrizione;
        this.lune = lune;
        annullaVoti();
        setAmato(false);
        this.mistico = mistico;
        tratti = new Tratti();
        setRomeo(false);
        annullaSegnalazioneAzzeccagarbugli();
        annullaSegnalazioneInquisitore();
        fazioneOriginale = fazione;
        annullaSegnalazioneBoia();
        annullaSegnalazioneOratore();
    }

    public boolean isCreaturaOmbra() { return getCategoria() == CREATURE_OMBRA || isTrattoPresente(CREATURA_OMBRA); }

    public String getNome() { return nome; }

    public Aura getAura()
    {
        Aura risultato = controlloMedium();
        if(controlloTrattiOscuri()) risultato = NERA;
        return risultato;
    }

    public String getDescrizione() { return descrizione; }

    public int getLune() { return lune; }

    public Categoria getCategoria() { return getFazione().getCategoria(); }

    public boolean isFazioneNegromante() { return false; }

    public Fazione getFazione() { return fazione; }

    public void incrementaVoti(int voti) { for(int i = 0; i < voti; i++) this.voti++; }

    public int getNumeroVoti()
    {
        int risultato = voti;
        if(isMaledetto()) risultato++;
        return risultato;
    }

    public void annullaVoti() { voti = 0; }

    public boolean isAmato() { return amato; }

    public void sceltaAngeloCustode()
    {
        setAmato(true);
        aggiungiProtezioneCreatureOmbra();
    }

    public void riconosciNegromante() { }

    public void cambiaFazione(Fazione fazione) { this.fazione = fazione; }

    public EsitoAttacco gildata() { return FALLITO; }

    public boolean isMistico() { return mistico; }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isAssassino() { return false; }

    public boolean isAngeloCustode() { return false; }

    public boolean isBardo() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return false; }

    public boolean isLupo() { return false; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isCapoGilda() { return false; }

    public boolean isPotereUtilizzato() { return false; }

    public void utilizzaPotere() { }

    public void riabilitaPotere() { }

    public boolean isCriminale() { return getFazione() == CRIMINALI; }

    public boolean isBoia() { return false; }

    public boolean isCitta() { return false; }

    public boolean isBorgomastro() { return false; }

    public boolean isBracconiere() { return false; }

    public boolean isCacciatore() { return false; }

    public boolean isVillaggio() { return false; }

    public boolean isCacciatoreDiVampiri() { return false; }

    public boolean isCappuccettoRosso() { return false; }

    public boolean isEremita() { return false; }

    public boolean isGhoul() { return false; }

    public void romeizzazione()
    {
        aggiungiProtezioneCreatureOmbra();
        setRomeo(true);
        cambiaFazione(AMANTI);
    }

    public boolean isAmanti() { return false; }

    public boolean isGiulietta() { return false; }

    public boolean isGiullare() { return false; }

    public boolean isGoblin() { return false; }

    public boolean isGuardia() { return false; }

    public boolean isGuardiaCorrotta() { return false; }

    public boolean isGuaritore() { return false; }

    public boolean isInquisizione() { return false; }

    public boolean isInquisitore() { return false; }

    public boolean isLadra() { return false; }

    public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco risultato = RIUSCITO;
        if(isProtezionePresente(ruolo)) risultato = getEsitoAttaccoRuoloProtetto(risultato);
        return risultato;
    }

    public boolean isProtezionePresente(Ruolo ruolo) { return tratti.isProtezionePresente(ruolo); }

    public boolean isLeprecauno() { return false; }

    public void perdiProtezioni() { tratti.perdiProtezioni(); }

    public boolean isMaledetto() { return isTrattoPresente(MALEDETTO); }

    public boolean isTrattoPresente(Tratto tratto) { return tratti.isPresente(tratto); }

    public void aggiungiTratti(Tratto... tratti) { for(Tratto tratto : tratti) this.tratti.aggiungi(tratto); }

    public boolean maledizione() { return tratti.maledizione(); }

    public void aggiungiProtezione(Ruolo... ruoli) { tratti.aggiungiProtezione(ruoli); }

    public void aggiungiProtezioneCreatureOmbra() { tratti.aggiungiProtezioneCreatureOmbra(); }

    public boolean isMago() { return false; }

    public boolean isMedium() { return false; }

    public Aura controlloMedium() { return aura; }

    public boolean isMegera() { return false; }

    public boolean isMercante() { return false; }

    public boolean isMonaco() { return false; }

    public boolean isNegromante() { return false; }

    public boolean isProtezioneLupiPresente() { return tratti.isProtezioneLupiPresente(); }

    public boolean isProtezioneNegromantePresente() { return tratti.isProtezioneNegromantePresente(); }

    public boolean isNosferatu() { return false; }

    public EsitoAttacco attaccoNosferatu()
    {
        EsitoAttacco risultato = RIUSCITO;
        if(isAttaccoNosferatuFallito()) risultato = FALLITO;
        gestioneConseguenzeNosferatu(risultato);
        return risultato;
    }

    public boolean isNonna() { return false; }

    public boolean isOratore() { return false; }

    public boolean isOste() { return false; }

    public boolean isPazzo() { return false; }

    public boolean isRomeo() { return romeo; }

    public boolean isPeccatore() { return false; }

    public boolean isPosseduto() { return false; }

    public boolean isPiccoloPopolo() { return false; }

    public boolean isPrete() { return false; }

    public boolean isSidhe() { return false; }

    public boolean isSpia() { return false; }

    public boolean isSensitiva() { return false; }

    public boolean isTemplare() { return false; }

    public void resettaAmato() { setAmato(false); }

    public EsitoAttacco attaccoAssassino()
    {
        EsitoAttacco esito = RIUSCITO;
        if(isAmato()) esito = FALLITO;
        return esito;
    }

    public EsitoPartita getEsitoPartita(Partita partita) { return NON_FINITO; }

    public EsitoAttacco vampirizzazione()
    {
        cambiaFazione(VAMPIRO);
        aggiungiTratti(NON_MORTO);
        return RIUSCITO;
    }

    public void eliminaTratto(Tratto tratto) { tratti.eliminaTratto(tratto); }

    public void resettaRomeo()
    {
        setRomeo(false);
        perdiProtezioni();
    }

    public boolean isSegnalatoAzzeccagarbugli() { return segnalazioneAzzeccagarbugli; }

    public void segnalazioneAzzeccagarbugli() { setSegnalazioneAzzeccagarbugli(true); }

    public void annullaSegnalazioneAzzeccagarbugli() { setSegnalazioneAzzeccagarbugli(false); }

    public boolean isInquisito() { return inquisito; }

    public void segnalazioneInquisitore() { setInquisito(true); }

    public void annullaSegnalazioneInquisitore() { setInquisito(false); }

    public void ripristinaFazioneOriginale() { cambiaFazione(fazioneOriginale); }

    public void segnalazioneBoia() { if(isMistico() || isCreaturaOmbra()) segnalatoBoia = true; }

    public void annullaSegnalazioneBoia() { segnalatoBoia = false; }

    public boolean isSegnalatoBoia() { return segnalatoBoia; }

    public boolean isSegnalatoOratore() { return segnalatoOratore; }

    public void segnalazioneOratore() { setSegnalazioneOratore(true); }

    public void annullaSegnalazioneOratore() { setSegnalazioneOratore(false); }

    public EsitoAttacco attaccoNegromante() { return RIUSCITO; }

    public EsitoControlloSensitiva controlloSensitiva() { return NON_VILLAGGIO; }

    public boolean isProtezioneNosferatuPresente() { return tratti.isProtezioneNosferatuPresente(); }

    public boolean isProtezionePossedutoPresente() { return tratti.isProtezionePossedutoPresente(); }

    private void setSegnalazioneOratore(boolean segnalatoOratore) { this.segnalatoOratore = segnalatoOratore; }

    private void setInquisito(boolean inquisito) { this.inquisito = inquisito; }

    private void setSegnalazioneAzzeccagarbugli(boolean segnalazioneAzzeccagarbugli)
    {
        this.segnalazioneAzzeccagarbugli = segnalazioneAzzeccagarbugli;
    }

    private void gestioneConseguenzeNosferatu(EsitoAttacco risultato)
    {
        switch(risultato)
        {
            case RIUSCITO -> trasformazioneNosferatu();
            case FALLITO -> perdiProtezioni();
        }
    }

    private boolean isAttaccoNosferatuFallito()
    {
        return isMistico() || tratti.isProtezioneNosferatuPresente() || isRomeo();
    }

    private void trasformazioneNosferatu()
    {
        aggiungiTratti(NON_MORTO);
        cambiaFazione(NOSFERATU);
    }

    private boolean controlloTrattiOscuri()
    {
        return isTrattoPresente(CREATURA_OMBRA) || isTrattoPresente(LUPO_MANNARO) || isTrattoPresente(NON_MORTO) || isMaledetto();
    }

    private void setAmato(boolean amato) { this.amato = amato; }

    private void setRomeo(boolean romeo) { this.romeo = romeo; }

    private EsitoAttacco getEsitoAttaccoRuoloProtetto(EsitoAttacco risultato)
    {
        if(isAmato()) perdiProtezioni();
        if(!isRomeo()) risultato = FALLITO;
        return risultato;
    }

}