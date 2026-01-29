package alessandro.stamera.wherewolfserver.classi;

public final class CapoBranco extends Lupo
{

    public CapoBranco()
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

}