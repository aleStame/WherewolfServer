package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Nonna extends Villaggio
{

    private Nonna()
    {
        super
        (
    "Nonna", BIANCA,
"Non ci sono più creature dell'ombra ed almeno un giocatore è in gioco. Non ci sono Criminali in gioco a fine partita o, se almeno un " +
          "Criminale è ancora in gioco, lo è anche almeno uno tra Guardia e Altra guardia",
     1, false
        );
    }

    public static Ruolo getInstance() { return new Nonna(); }

}