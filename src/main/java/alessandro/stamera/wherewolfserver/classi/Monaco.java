package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Monaco extends Villaggio
{

    public Monaco() { super("Monaco", BIANCA, null, 0, true); }

    public static Ruolo getInstance() { return new Monaco(); }

}