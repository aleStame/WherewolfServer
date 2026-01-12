package alessandro.stamera.wherewolfserver.classi;

public enum TipoContadino
{

    NORMALE("Contadino normale"), EROE("Contadino eroe"), MOSTRO("Contadino mostro"),
    LUPO("Contadino discendente dei lupi");

    private final String descrizione;

    TipoContadino(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static TipoContadino getTipoContadino(String descrizione)
    {
        int posizione = -1;
        for(int i = 0; i < values().length && posizione == -1; i++) if(descrizione.equals(values()[i].toString())) posizione = i;
        return values()[posizione];
    }

}