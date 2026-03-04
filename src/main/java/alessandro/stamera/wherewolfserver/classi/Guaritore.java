package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Guaritore extends Villaggio
{

    public Guaritore() { super("Guaritore", BIANCA, null, 1, true); }

    @Override public boolean isGuaritore() { return true; }

}
