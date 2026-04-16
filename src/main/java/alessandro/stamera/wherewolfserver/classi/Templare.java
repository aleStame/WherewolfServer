package alessandro.stamera.wherewolfserver.classi;

public final class Templare extends Inquisizione
{

    private Templare() { super("Templare", null, null); }

    public static Ruolo getInstance() { return new Templare(); }

}