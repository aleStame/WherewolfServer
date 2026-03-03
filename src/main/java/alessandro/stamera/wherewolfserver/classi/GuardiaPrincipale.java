package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class GuardiaPrincipale extends Villaggio
{

    public GuardiaPrincipale() { super("Guardia", BIANCA, null, 2, false); }

    @Override public boolean isGuardia() { return true; }

}