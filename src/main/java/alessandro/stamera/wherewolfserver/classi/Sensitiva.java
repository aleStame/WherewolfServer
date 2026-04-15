package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Sensitiva extends Villaggio
{

    private Sensitiva() { super("Sensitiva", BIANCA, null, 0, false); }

    public static Ruolo getInstance() { return new Sensitiva(); }

}