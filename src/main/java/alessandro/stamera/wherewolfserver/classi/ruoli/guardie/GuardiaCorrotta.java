package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

public final class GuardiaCorrotta extends Criminale
{

    private GuardiaCorrotta()
    {
        super("Guardia corrotta", NERA, "La prima notte riconosce le altre guardie e in seguito gli altri criminali.");
    }

    @Override public boolean isGuardiaCorrotta() { return true; }

    public static Ruolo getInstance() { return new GuardiaCorrotta(); }

}
