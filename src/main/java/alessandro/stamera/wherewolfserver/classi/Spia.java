package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Spia extends Criminale
{

    private Spia() { super("Spia", BIANCA, null); }

    public static Ruolo getInstance() { return new Spia(); }

}