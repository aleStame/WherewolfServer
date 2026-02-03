package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;

public class Ruolo
{

    private final String nome, descrizione;

    private Fazione fazione;

    private final Aura aura;

    private final int lune;

    private int voti;

    private boolean amato, assassinio;

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
    }

    public String getNome() { return nome; }

    public Aura getAura() { return aura; }

    public String getDescrizione() { return descrizione; }

    public int getLune() { return lune; }

    public Categoria getCategoria() { return getFazione().getCategoria(); }

    public Fazione getFazione() { return fazione; }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public void incrementaVoti() { voti++; }

    public int getNumeroVoti() { return voti; }

    public void annullaVoti() { voti = 0; }

    public boolean isAmato() { return amato; }

    public void sceltaAngeloCustode() { setAmato(true); }

    public boolean isAngeloCustode() { return false; }

    public boolean isAssassino() { return false; }

    public boolean assassinioAvvenuto() { return assassinio; }

    public void eseguiAssassinio() { setAssassinio(true); }

    public boolean isBecchino() { return false; }

    public void riconosciNegromante() { cambiaFazione(NEGROMANTE); }

    public void cambiaFazione(Fazione fazione) { this.fazione = fazione; }

    public boolean isBoccaDiRosa() { return false; }

    public boolean gildata()
    {
        cambiaFazione(CRIMINALI);
        return true;
    }

    public boolean isCapoGilda() { return false; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isMistico() { return mistico; }

    public void segnalazioneAzzeccagarbugli() { if(controlloFazioneAzzeccagarbugli()) annullaVoti(); }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isAccusato() { return true; }

    private void setAssassinio(boolean assassinio) { this.assassinio = assassinio; }

    private void setAmato(boolean amato) { this.amato = amato; }

    private boolean controlloFazioneAzzeccagarbugli() { return controlloFazione(CITTA) || controlloFazione(CRIMINALI); }

    private boolean controlloFazione(Fazione fazione) { return getFazione() == fazione; }

}