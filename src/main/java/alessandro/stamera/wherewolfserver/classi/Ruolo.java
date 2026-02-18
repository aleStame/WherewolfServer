package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;

public class Ruolo
{

    private final String nome, descrizione;

    private Fazione fazione;

    private final Aura aura;

    private final int lune;

    private int voti;

    private boolean amato, accusato;

    private final boolean mistico;

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
    }

    public String getNome() { return nome; }

    public Aura getAura() { return aura; }

    public String getDescrizione() { return descrizione; }

    public int getLune() { return lune; }

    public Categoria getCategoria() { return getFazione().getCategoria(); }

    public Fazione getFazione() { return fazione; }

    public void incrementaVoti(int voti) { for(int i = 0; i < voti; i++) this.voti++; }

    public int getNumeroVoti() { return voti; }

    public void annullaVoti() { voti = 0; }

    public boolean isAmato() { return amato; }

    public void sceltaAngeloCustode() { setAmato(true); }

    public void riconosciNegromante() { cambiaFazione(NEGROMANTE); }

    public void cambiaFazione(Fazione fazione) { this.fazione = fazione; }

    public void gildata() { }

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

    public boolean isVillaggio() { return true; }

    private void setAccusato(boolean accusato) { this.accusato = accusato; }

    private void setAmato(boolean amato) { this.amato = amato; }

}