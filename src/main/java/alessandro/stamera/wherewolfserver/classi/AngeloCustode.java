package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;

public final class AngeloCustode extends Ruolo
{

    public AngeloCustode()
    {
        super
        (
      "Angelo custode", AMANTI, BIANCA,
  "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode.",
        -1
        );
    }

}