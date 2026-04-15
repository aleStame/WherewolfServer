package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Sensitiva extends Villaggio
{

    private Sensitiva()
    {
        super
        (
    "Sensitiva", BIANCA,
"Se è in gioco, lo è al posto della Veggente. Ogni notte indica un giocatore (compresa sé stessa) e scopre se possiede fazione " +
          "Villaggio. I giocatori maledetti hanno fazione Maledetto solo ai fini delle condizioni di fine gioco, quindi vengono visti dalla " +
          "Sensitiva con la loro fazione originale",
     1, true
        );
    }

    @Override public boolean isSensitiva() { return true; }

    public static Ruolo getInstance() { return new Sensitiva(); }

}