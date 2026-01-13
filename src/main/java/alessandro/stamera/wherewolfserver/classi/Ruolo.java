package alessandro.stamera.wherewolfserver.classi;

public class Ruolo
{

    private final String nome, descrizione;

    private final Fazione fazione;

    private final Aura aura;

    private final int lune;

    public Ruolo(String nome, Fazione fazione, Aura aura, String descrizione, int lune)
    {
        this.nome = nome;
        this.fazione = fazione;
        this.aura = aura;
        this.descrizione = descrizione;
        this.lune = lune;
    }

    public String getNome() { return nome; }

    public Aura getAura() { return aura; }

    public String getDescrizione() { return descrizione; }

    public int getLune() { return lune; }

    public Categoria getCategoria() { return getFazione().getCategoria(); }

    public Fazione getFazione() { return fazione; }

    public boolean isContadino() { return true; }

}