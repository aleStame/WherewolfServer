package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class Peccatore extends Villaggio
{

    private Peccatore() { super("Peccatore", NERA, null, 1, false); }

    public static Ruolo getInstance() { return new Peccatore(); }

}