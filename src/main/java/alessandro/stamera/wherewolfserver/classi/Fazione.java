package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;

public enum Fazione
{

    NESSUNA(), LUPO_BRANCO("Lupi del branco", CREATURE_OMBRA), LUPO_SOLITARIO("Lupo solitario", CREATURE_OMBRA),
    VAMPIRO("Vampiro", CREATURE_OMBRA), NOSFERATU("Nosferatu", CREATURE_OMBRA),
    NEGROMANTE("Negromante", CREATURE_OMBRA), POSSEDUTO("Posseduto", CREATURE_OMBRA),
    VILLAGGIO("Villaggio", UOMINI), CITTA("Città", UOMINI), CRIMINALI("Criminali", UOMINI),
    AMANTI("Amanti", UOMINI), INQUISIZIONE("Inquisizione", UOMINI);

    private final String descrizione;

    private final Categoria categoria;

    Fazione() { this("-", Categoria.NESSUNA); }

    Fazione(String descrizione, Categoria categoria)
    {
        this.descrizione = descrizione;
        this.categoria = categoria;
    }

    @Override public String toString() { return descrizione; }

    public Categoria getCategoria() { return categoria; }

    public static Fazione getFazione(String descrizione) { return getFazione(getPosizione(descrizione)); }

    private static int getPosizione(String descrizione)
    {
        int posizione = -1;
        for(int i = 0; i < getNumeroFazioni() && posizione == -1; i++) if(descrizione.equals(getDescrizione(i))) posizione = i;
        return posizione;
    }

    private static int getNumeroFazioni() { return values().length; }

    private static String getDescrizione(int posizione) { return getFazione(posizione).toString(); }

    private static Fazione getFazione(int posizione) { return values()[posizione]; }

}