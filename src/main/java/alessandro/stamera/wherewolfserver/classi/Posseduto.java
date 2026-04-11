package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.POSSEDUTO;

public final class Posseduto extends Ruolo
{

    private Posseduto() { super("Posseduto", POSSEDUTO, NERA, null, 0, false); }

    public static Ruolo getInstance() { return new Posseduto(); }

}