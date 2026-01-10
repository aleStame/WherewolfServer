package alessandro.stamera.wherewolfserver.classi;

public enum Aura
{

    BIANCA("Bianca"), NERA("Nera");

    private final String descrizione;

    Aura(String descrizione){ this.descrizione = descrizione; }

    @Override public String toString() { return "Aura: " + descrizione; }

}