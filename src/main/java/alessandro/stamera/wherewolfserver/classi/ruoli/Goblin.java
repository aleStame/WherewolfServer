package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.PiccoloPopolo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

public final class Goblin extends PiccoloPopolo
{

    private Goblin()
    {
        super("Goblin", NERA, "La prima notte riconosce le altre creature del piccolo popolo ed è protetto da tutti i mistici.");
    }

    @Override public boolean isGoblin() { return true; }

    public static Ruolo getInstance() { return new Goblin(); }

}