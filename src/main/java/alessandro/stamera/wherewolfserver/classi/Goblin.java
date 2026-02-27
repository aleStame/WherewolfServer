package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;

public class Goblin extends Ruolo
{

    public Goblin() { super("Goblin", NESSUNA, NERA, null, 0, true); }

    @Override public boolean isGoblin() { return true; }

}