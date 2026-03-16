package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public final class GuardiaCorrotta extends Guardia
{

    private GuardiaCorrotta()
    {
        super("Guardia corrotta", NERA, "La prima notte riconosce le altre guardie e in seguito gli altri criminali.");
        cambiaFazione(CRIMINALI);
    }

    @Override public boolean isGuardiaCorrotta() { return true; }

    public static Ruolo getInstance() { return new GuardiaCorrotta(); }

}
