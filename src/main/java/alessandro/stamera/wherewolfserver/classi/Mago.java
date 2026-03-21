package alessandro.stamera.wherewolfserver.classi;

public final class Mago extends Villaggio
{

    public Mago() { super("Mago", null, null, 0, false); }

    public static Ruolo getInstance() { return new Mago(); }

}
