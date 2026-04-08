package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Oratore extends Citta
{

    private Oratore() { super("Oratore", BIANCA, null); }

    public static Ruolo getInstance() { return new Oratore(); }

}