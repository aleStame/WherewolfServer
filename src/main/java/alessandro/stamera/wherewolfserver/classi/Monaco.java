package alessandro.stamera.wherewolfserver.classi;

public final class Monaco extends Villaggio
{

    public Monaco() { super("Monaco", null, null, 0, true); }

    public static Ruolo getInstance() { return new Monaco(); }

}