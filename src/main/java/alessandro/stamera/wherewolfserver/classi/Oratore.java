package alessandro.stamera.wherewolfserver.classi;

public final class Oratore extends Citta
{

    private Oratore() { super("Oratore", null, null); }

    public static Ruolo getInstance() { return new Oratore(); }

}