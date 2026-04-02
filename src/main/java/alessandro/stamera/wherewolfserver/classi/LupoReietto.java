package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;

public final class LupoReietto extends Lupo
{

    private LupoReietto()
    {
        super
        (
    "Lupo reietto",
"La prima notte individua il Traditore e la Megera e riconosce i lupi del branco. Dalla prima notte, se è il lupo più potente in " +
          "gioco, durante il turno dei lupi, può indicare un giocatore che viene ucciso. É protetto dal Capo branco e perde la partita se questi " +
          "alla fine è ancora in gioco.",
     3);
    }

    @Override public boolean isLupoReietto() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return FALLITO; }

    public static Ruolo getInstance() { return new LupoReietto(); }

}