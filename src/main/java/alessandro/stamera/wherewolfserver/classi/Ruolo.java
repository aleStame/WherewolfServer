package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.LUPO_MANNARO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.MALEDETTO;

public class Ruolo
{

    private final String nome, descrizione;

    private Fazione fazione;

    private final Aura aura;

    private final int lune;

    private int voti;

    private boolean amato, accusato, romeo;

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
        libera();
        tratti = new Tratti();
        setRomeo(false);
    }

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

    public void riconosciNegromante() { cambiaFazione(NEGROMANTE); }

    public void cambiaFazione(Fazione fazione) { this.fazione = fazione; }

    public void gildata() { }

    public void segnalazioneInquisitore() { if(isMistico()) accusa(); }

    public boolean isMistico() { return mistico; }

    public boolean isAccusato() { return accusato; }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isAssassino() { return false; }

    public boolean isAngeloCustode() { return false; }

    public boolean isBardo() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return false; }

    public boolean isLupo() { return false; }

    public void segnalazioneAzzeccagarbugli() { accusa(); }

    public void accusa() { setAccusato(true); }

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

    public boolean isCriminale() { return false; }

    public boolean isBoia() { return false; }

    public void libera() { setAccusato(false); }

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

    public boolean attacco(Ruolo ruolo)
    {
        boolean esito = !isProtezionePresente(ruolo);
        if(!esito && romeo && ruolo.isLupo()) esito = true;
        return esito;
    }

    public boolean isProtezionePresente(Ruolo ruolo) { return tratti.isProtezionePresente(ruolo); }

    public boolean isLeprecauno() { return false; }

    public void perdiProtezioni() { tratti.perdiProtezioni(); }

    public boolean isMaledetto() { return isTrattoPresente(MALEDETTO); }

    public boolean isTrattoPresente(Tratto tratto) { return tratti.isPresente(tratto); }

    public void aggiungiTratti(Tratto... tratti) { for(Tratto tratto : tratti) this.tratti.aggiungi(tratto); }

    public void maledizione() { tratti.maledizione(); }

    public void aggiungiProtezione(Ruolo... ruoli) { tratti.aggiungiProtezione(ruoli); }

    public void aggiungiProtezioneCreatureOmbra() { tratti.aggiungiProtezioneCreatureOmbra(); }

    public boolean isMago() { return false; }

    public boolean isMedium() { return false; }

    public Aura controlloMedium() { return aura; }

    public boolean isMegera() { return false; }

    public boolean isMercante() { return false; }

    public boolean isMonaco() { return false; }

    private boolean controlloTrattiOscuri()
    {
        return isTrattoPresente(CREATURA_OMBRA) || isTrattoPresente(LUPO_MANNARO) || isMaledetto();
    }

    private void setAccusato(boolean accusato) { this.accusato = accusato; }

    private void setAmato(boolean amato) { this.amato = amato; }

    private void setRomeo(boolean romeo) { this.romeo = romeo; }

}