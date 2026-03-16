package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class Assassino extends Criminale
{

    private Assassino()
    {
        super
        (
    "Assassino", NERA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso."
        );
    }

    @Override public boolean isAssassino() { return true; }

    public static Ruolo getInstance() { return new Assassino(); }

}
