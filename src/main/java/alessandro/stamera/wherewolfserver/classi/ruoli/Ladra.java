package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Ladra extends Criminale
{

    private Ladra()
    {
        super
        (
    "Ladra", BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
          "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra."
        );
    }

    @Override public boolean isLadra() { return true; }

    public static Ruolo getInstance() { return new Ladra(); }

}