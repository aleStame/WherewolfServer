package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public class Strega extends Villaggio
{

    private Strega() { super("Strega", null, null, 0, false); }

    public static Ruolo getInstance() { return new Strega(); }

}