package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import static java.util.Arrays.stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;

public enum Fazione
{

    NESSUNA(), LUPO_BRANCO("Lupi del branco", CREATURE_OMBRA), LUPO_SOLITARIO("Lupo solitario", CREATURE_OMBRA),
    VAMPIRO("Vampiro", CREATURE_OMBRA), NOSFERATU("Nosferatu", CREATURE_OMBRA),
    NEGROMANTE("Negromante", CREATURE_OMBRA), POSSEDUTO("Posseduto", CREATURE_OMBRA),
    VILLAGGIO("Villaggio", UOMINI), CITTA("Città", UOMINI), CRIMINALI("Criminali", UOMINI),
    AMANTI("Amanti", UOMINI), INQUISIZIONE("Inquisizione", UOMINI);

    private static final int NON_TROVATO = -1;

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

    public static Fazione getFazione(String descrizione)
    {
        return stream(values()).filter(fazione -> fazione.toString().equals(descrizione)).findFirst().get();
    }

}