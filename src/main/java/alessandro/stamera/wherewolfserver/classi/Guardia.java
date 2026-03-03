package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Guardia extends Villaggio
{

    public Guardia() { super("Guardia", BIANCA, null, 2, false); }

    @Override public boolean isGuardia() { return true; }

}