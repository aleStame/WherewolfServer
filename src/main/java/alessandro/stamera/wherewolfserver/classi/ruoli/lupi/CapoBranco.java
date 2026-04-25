package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public final class CapoBranco extends Lupo
{

    private CapoBranco()
    {
        super
        (
    "Capo branco",
"La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore, e questi " +
          "viene ucciso.",
     1
        );
    }

    @Override public boolean isCapoBranco() { return true; }

    public static Ruolo getInstance() { return new CapoBranco(); }

}