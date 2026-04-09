package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class Peccatore extends Villaggio
{

    private Peccatore() { super("Peccatore", NERA, null, 0, true); }

    public static Ruolo getInstance() { return new Peccatore(); }

}