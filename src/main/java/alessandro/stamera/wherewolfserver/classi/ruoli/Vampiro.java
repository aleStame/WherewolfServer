package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public final class Vampiro extends CreatureOmbra
{

    private Vampiro() { super("Vampiro", null, null, null, 0, true); }

    public static Ruolo getInstance() { return new Vampiro(); }

}