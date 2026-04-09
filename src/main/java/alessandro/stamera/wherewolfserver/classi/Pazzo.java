package alessandro.stamera.wherewolfserver.classi;

public final class Pazzo extends Ruolo
{

    private Pazzo() { super("Pazzo", null, null, null, 0, true); }

    public static Ruolo getInstance() { return new Pazzo(); }

}