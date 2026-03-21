package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;

public class Goblin extends Ruolo
{

    private Goblin()
    {
        super
        (
    "Goblin", NESSUNA, NERA, "La prima notte riconosce le altre creature del piccolo popolo ed è protetto da tutti i mistici.",
     1, true
        );
    }

    @Override public boolean isGoblin() { return true; }

    public static Ruolo getInstance() { return new Goblin(); }

}