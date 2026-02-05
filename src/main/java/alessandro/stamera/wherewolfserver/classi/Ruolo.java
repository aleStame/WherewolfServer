package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public abstract class Ruolo
{

    private final String nome, descrizione;

    private Fazione fazione;

    private final Aura aura;

    private final int lune;

    private int voti;

    private boolean amato, assassinio, accusato;

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
        setAssassinio(false);
        this.mistico = mistico;
        setAccusato(false);
    }

    public String getNome() { return nome; }

    public Aura getAura() { return aura; }

    public String getDescrizione() { return descrizione; }

    public int getLune() { return lune; }

    public Categoria getCategoria() { return getFazione().getCategoria(); }

    public Fazione getFazione() { return fazione; }

    public void incrementaVoti() { voti++; }

    public int getNumeroVoti() { return voti; }

    public void annullaVoti() { voti = 0; }

    public boolean isAmato() { return amato; }

    public void sceltaAngeloCustode() { setAmato(true); }

    public boolean assassinioAvvenuto() { return assassinio; }

    public void eseguiAssassinio() { setAssassinio(true); }

    public void riconosciNegromante() { cambiaFazione(NEGROMANTE); }

    public void cambiaFazione(Fazione fazione) { this.fazione = fazione; }

    public boolean gildata()
    {
        cambiaFazione(CRIMINALI);
        return true;
    }

    public boolean isMistico() { return mistico; }

    public boolean isAccusato() { return accusato; }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isAssassino() { return false; }

    public boolean isAngeloCustode() { return false; }

    public boolean isBardo() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return false; }

    void setAccusato(boolean accusato) { this.accusato = accusato; }

    abstract void segnalazioneAzzeccagarbugli();

    abstract boolean isContadino();

    abstract boolean isContadinoNormale();

    abstract boolean isContadinoMostro();

    abstract boolean isContadinoEroe();

    abstract boolean isContadinoLupo();

    abstract boolean isCapoGilda();

    abstract boolean isCapoBranco();

    abstract boolean isLupoBranco();

    abstract boolean isGiovaneLupo();

    abstract boolean isLupoReietto();

    abstract boolean isLupoSolitario();

    abstract boolean isLupo();

    private void setAssassinio(boolean assassinio) { this.assassinio = assassinio; }

    private void setAmato(boolean amato) { this.amato = amato; }

}