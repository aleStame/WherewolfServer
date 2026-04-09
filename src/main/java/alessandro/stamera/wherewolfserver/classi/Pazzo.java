package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Pazzo extends Ruolo
{

    private Pazzo() { super("Pazzo", null, BIANCA, null, 0, true); }

    public static Ruolo getInstance() { return new Pazzo(); }

}