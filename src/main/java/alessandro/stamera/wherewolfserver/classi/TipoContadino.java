package alessandro.stamera.wherewolfserver.classi;

public enum TipoContadino
{

    NORMALE("Contadino normale"), EROE("Contadino eroe"), MOSTRO("Contadino mostro"),
    LUPO("Contadino discendente dei lupi");

    private final String descrizione;

    TipoContadino(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static TipoContadino getTipoContadino(String descrizione) { return null; }

}