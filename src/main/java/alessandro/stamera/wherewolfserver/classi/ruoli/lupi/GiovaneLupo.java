package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public class GiovaneLupo extends Lupo
{

    private GiovaneLupo()
    {
        super
        (
    "Giovane lupo",
"La prima notte individua il Traditore e riconosce i lupi del branco e nelle altre apre gli occhi durante il turno dei lupi mannari. " +
          "Se viene messo al rogo, la notte successiva i lupi del branco attaccheranno due volte",
     1
        );
    }

    @Override public boolean isGiovaneLupo() { return true; }

    public static Ruolo getInstance() { return new GiovaneLupo(); }

}