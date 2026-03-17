package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.LUPO_MANNARO;

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
        aggiungiTratti(CREATURA_OMBRA, LUPO_MANNARO);
    }

    @Override public boolean isGiovaneLupo() { return true; }

    public static Ruolo getInstance() { return new GiovaneLupo(); }

}