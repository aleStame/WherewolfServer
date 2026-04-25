package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

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