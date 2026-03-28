package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;

public final class Negromante extends Ruolo
{

    private Negromante() { super("Negromante", NEGROMANTE, null, null, 0, false); }

    public static Ruolo getInstance() { return new Negromante(); }

}