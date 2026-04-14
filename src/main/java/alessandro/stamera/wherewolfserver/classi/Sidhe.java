package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Sidhe extends PiccoloPopolo
{

    private Sidhe() { super("Sidhe", BIANCA, null); }

    public static Ruolo getInstance() { return new Sidhe(); }

}