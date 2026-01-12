package alessandro.stamera.wherewolfserver.classi;

public enum Categoria
{

    NESSUNA("-"), CREATURE_OMBRA("Creature dell'ombra"), UOMINI("Uomini");

    private static final int NON_TROVATO = -1;

    private final String descrizione;

    Categoria(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static Categoria getCategoria(String descrizione) { return getCategoria(getPosizioneCategoria(descrizione)); }

    private static int getPosizioneCategoria(String descrizione)
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroCategorie() && posizione == NON_TROVATO; i++) if(descrizione.equals(getDescrizione(i))) posizione = i;
        return posizione;
    }

    private static String getDescrizione(int posizione) { return getCategoria(posizione).toString(); }

    private static Categoria getCategoria(int posizione) { return values()[posizione]; }

    private static int getNumeroCategorie() { return values().length; }

}
