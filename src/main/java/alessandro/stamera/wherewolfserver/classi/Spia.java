package alessandro.stamera.wherewolfserver.classi;

public final class Spia extends Criminale
{

    private Spia() { super("Spia", null, null); }

    public static Ruolo getInstance() { return new Spia(); }

}