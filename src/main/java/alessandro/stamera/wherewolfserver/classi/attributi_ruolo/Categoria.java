package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import static java.util.Arrays.stream;

public enum Categoria
{

    NESSUNA("-"), CREATURE_OMBRA("Creature dell'ombra"), UOMINI("Uomini");

    private final String descrizione;

    Categoria(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static Categoria getCategoria(String descrizione)
    {
        return stream(values()).filter(categoria -> categoria.toString().equals(descrizione)).findFirst().get();
    }

}