package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoControlloSensitiva.NON_VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;

public final class GuardiaCorrotta extends Guardia
{

    private GuardiaCorrotta()
    {
        super("Guardia corrotta", NERA, "La prima notte riconosce le altre guardie e in seguito gli altri criminali.");
        cambiaFazione(CRIMINALI);
    }

    @Override public boolean isGuardiaCorrotta() { return true; }

    @Override public EsitoControlloSensitiva controlloSensitiva() { return NON_VILLAGGIO; }

    public static Ruolo getInstance() { return new GuardiaCorrotta(); }

}
