package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Templare extends Inquisizione
{

    private Templare() { super("Templare", BIANCA, null); }

    public static Ruolo getInstance() { return new Templare(); }

}