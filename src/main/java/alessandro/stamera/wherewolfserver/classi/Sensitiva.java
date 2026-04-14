package alessandro.stamera.wherewolfserver.classi;

public final class Sensitiva extends Villaggio
{

    private Sensitiva() { super("Sensitiva", null, null, 0, false); }

    public static Ruolo getInstance() { return new Sensitiva(); }

}