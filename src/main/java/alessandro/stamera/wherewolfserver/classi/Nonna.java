package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Nonna extends Villaggio
{

    private Nonna() { super("Nonna", BIANCA, null, 1, false); }

    public static Ruolo getInstance() { return new Nonna(); }

}