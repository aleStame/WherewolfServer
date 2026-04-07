package alessandro.stamera.wherewolfserver.classi;

public final class Nonna extends Villaggio
{

    private Nonna() { super("Nonna", null, null, 0, true); }

    public static Ruolo getInstance() { return new Nonna(); }

}