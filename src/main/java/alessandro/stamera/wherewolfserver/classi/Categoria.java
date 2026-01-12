package alessandro.stamera.wherewolfserver.classi;

public enum Categoria
{

    NESSUNA("-"), CREATURE_OMBRA("Creature dell'ombra"), UOMINI("Uomini");

    private final String descrizione;

    Categoria(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static Categoria getCategoria(String descrizione) { return values()[getPosizioneCategoria(descrizione)]; }

    private static int getPosizioneCategoria(String descrizione)
    {
        int posizione = -1;
        for(int i = 0; i < values().length && posizione == -1; i++) if(descrizione.equals(values()[i].toString())) posizione = i;
        return posizione;
    }

}
