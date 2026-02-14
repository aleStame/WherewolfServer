package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public final class Bracconiere extends Ruolo
{

    public Bracconiere()
    {
        super
        (
    "Bracconiere", VILLAGGIO, BIANCA,
"La prima notte scopre quanti lupi del branco sono in gioco e se è in gioco il Lupo solitario. Durante il turno dei lupi mannari può " +
          "segnalare la sua presenza: se in gioco è rimasto soltanto un lupo mannaro, questi non può attaccare",
     3, false
        );
    }

    @Override public boolean isBracconiere() { return true; }

}