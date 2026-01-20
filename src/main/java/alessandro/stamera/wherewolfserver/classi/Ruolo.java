package alessandro.stamera.wherewolfserver.classi;

public class Ruolo
{

    private final String nome, descrizione;

    private final Fazione fazione;

    private final Aura aura;

    private final int lune;

    private int voti;

    private boolean amato, assassinio;

    public Ruolo(String nome, Fazione fazione, Aura aura, String descrizione, int lune)
    {
        this.nome = nome;
        this.fazione = fazione;
        this.aura = aura;
        this.descrizione = descrizione;
        this.lune = lune;
        annullaVoti();
        setAmato(false);
        setAssassinio(false);
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

    public void eseguiAssassinio() { if(isAssassino() && !assassinioAvvenuto()) setAssassinio(true); }

    private void setAmato(boolean amato) { this.amato = amato; }

    public boolean isBecchino() { return false; }

    private void setAssassinio(boolean assassinio) { this.assassinio = assassinio; }

}