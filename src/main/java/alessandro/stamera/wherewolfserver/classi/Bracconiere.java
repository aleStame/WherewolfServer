package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public final class Bracconiere extends Ruolo
{

    public Bracconiere() { super("Bracconiere", VILLAGGIO, BIANCA, null, 3, false); }

    @Override public boolean isBracconiere() { return true; }

}