package alessandro.stamera.wherewolfserver.classi;

public class GiovaneLupo extends Lupo
{

    public GiovaneLupo()
    {
        super
        (
    "Giovane lupo",
"La prima notte individua il Traditore e riconosce i lupi del branco e nelle altre apre gli occhi durante il turno dei lupi mannari. " +
          "Se viene messo al rogo, la notte successiva i lupi del branco attaccheranno due volte",
     1);
    }

    @Override public boolean isGiovaneLupo() { return !super.isGiovaneLupo(); }

}