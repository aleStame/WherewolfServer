package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;

public class Ghoul extends Ruolo
{

    private Ghoul() { super("Ghoul", NESSUNA, BIANCA, null, 2, false); }

    @Override public boolean isGhoul() { return true; }

    public static Ruolo getInstance() { return new Ghoul(); }

}