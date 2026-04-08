package alessandro.stamera.wherewolfserver.classi;

public final class Oste extends Villaggio
{

    private Oste() { super("Oste", null, null, 0, true); }

    public static Ruolo getInstance() { return new Oste(); }

}