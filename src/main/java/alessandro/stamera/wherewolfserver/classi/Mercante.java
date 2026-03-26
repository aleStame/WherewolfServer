package alessandro.stamera.wherewolfserver.classi;

public final class Mercante extends Citta
{

    public Mercante() { super("Mercante", null, null); }

    public static Ruolo getInstance() { return new Mercante(); }

}