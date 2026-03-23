package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Medium extends Villaggio
{

    public Medium() { super("Medium", BIANCA, null, 0, false); }

    public static Ruolo getInstance() { return new Medium(); }

}
