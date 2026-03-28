package alessandro.stamera.wherewolfserver.classi;

public final class Negromante extends Ruolo
{

    private Negromante() { super("Negromante", null, null, null, 0, false); }

    public static Ruolo getInstance() { return new Negromante(); }

}