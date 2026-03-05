package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Inquisitore extends Inquisizione
{

    public Inquisitore() { super("Inquisitore", BIANCA, null); }

    @Override public boolean isInquisitore() { return true; }

}