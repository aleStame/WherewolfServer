package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Oste extends Villaggio
{

    private Oste() { super("Oste", BIANCA, null, 0, true); }

    public static Ruolo getInstance() { return new Oste(); }

}