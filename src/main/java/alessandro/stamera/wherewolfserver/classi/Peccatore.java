package alessandro.stamera.wherewolfserver.classi;

public final class Peccatore extends Villaggio
{

    private Peccatore() { super("Peccatore", null, null, 0, true); }

    public static Ruolo getInstance() { return new Peccatore(); }

}