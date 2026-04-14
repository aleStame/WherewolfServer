package alessandro.stamera.wherewolfserver.classi;

public final class Sidhe extends PiccoloPopolo
{

    private Sidhe() { super("Sidhe", null, null); }

    public static Ruolo getInstance() { return new Sidhe(); }

}