package alessandro.stamera.wherewolfserver.classi;

public final class Nosferatu extends Ruolo
{

    private Nosferatu() { super("Nosferatu", null, null, null, 0, true); }

    public static Ruolo getInstance() { return new Nosferatu(); }

}