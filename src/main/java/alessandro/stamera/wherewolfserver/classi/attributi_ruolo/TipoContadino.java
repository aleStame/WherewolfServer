package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

public enum TipoContadino
{

    NORMALE("Contadino normale"), EROE("Contadino eroe"), MOSTRO("Contadino mostro"),
    LUPO("Contadino discendente dei lupi");

    private static final int NON_TROVATO = -1;

    private final String descrizione;

    TipoContadino(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static TipoContadino getTipoContadino(String descrizione)
    {
        return getTipoContadino(getPosizioneContadino(descrizione));
    }

    private static int getPosizioneContadino(String descrizione)
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < values().length && posizione == NON_TROVATO; i++) if(descrizione.equals(getDescrizioneContadino(i))) posizione = i;
        return posizione;
    }

    private static String getDescrizioneContadino(int posizione) { return getTipoContadino(posizione).toString(); }

    private static TipoContadino getTipoContadino(int posizione) { return values()[posizione]; }

}