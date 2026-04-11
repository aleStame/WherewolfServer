package alessandro.stamera.wherewolfserver.classi;

public final class Posseduto extends Ruolo
{

    private Posseduto() { super("Posseduto", null, null, null, 0, true); }

    public static Ruolo getInstance() { return new Posseduto(); }

}