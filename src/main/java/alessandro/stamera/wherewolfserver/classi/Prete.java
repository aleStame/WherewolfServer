package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Prete extends Villaggio
{

    private Prete()
    {
        super
        (
    "Prete", BIANCA,
"La prima notte individua il Peccatore e scopre se Bocca di rosa e il Posseduto sono in gioco. È protetto dal Posseduto e, se viene " +
          "attaccato, viene avvisato e gli fa perdere tutti i poteri.",
     1, true
        );
    }

    public static Ruolo getInstance() { return new Prete(); }

}