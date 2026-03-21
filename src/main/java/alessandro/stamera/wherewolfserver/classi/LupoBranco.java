package alessandro.stamera.wherewolfserver.classi;

public final class LupoBranco extends Lupo
{

    private LupoBranco()
    {
        super
        (
    "Lupo del branco",
"La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore che verrà " +
          "ucciso se è il lupo più potente in gioco.",
     1);
    }

    @Override public boolean isLupoBranco() { return true; }

    public static Ruolo getInstance() { return new LupoBranco(); }

}