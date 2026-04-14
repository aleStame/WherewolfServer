package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Spia extends Criminale
{

    private Spia() { super("Spia", BIANCA, null); }

    @Override public boolean isSpia() { return true; }

    public static Ruolo getInstance() { return new Spia(); }

}