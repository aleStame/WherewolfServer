package alessandro.stamera.wherewolfserver.classi;

public enum Categoria
{

    NESSUNA("-"), CREATURE_OMBRA("Creature dell'ombra"), UOMINI("Uomini");

    private final String descrizione;

    Categoria(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

}
