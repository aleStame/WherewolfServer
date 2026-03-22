package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Mago extends Villaggio
{

    public Mago() { super("Mago", BIANCA, null, 1, false); }

    public static Ruolo getInstance() { return new Mago(); }

}
