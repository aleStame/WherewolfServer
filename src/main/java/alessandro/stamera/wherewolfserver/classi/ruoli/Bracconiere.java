package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Bracconiere extends Villaggio
{

    private Bracconiere()
    {
        super
        (
    "Bracconiere", BIANCA,
"La prima notte scopre quanti lupi del branco sono in gioco e se è in gioco il Lupo solitario. Durante il turno dei lupi mannari può " +
          "segnalare la sua presenza: se in gioco è rimasto soltanto un lupo mannaro, questi non può attaccare",
     3, false
        );
    }

    @Override public boolean isBracconiere() { return true; }

    public static Ruolo getInstance() { return new Bracconiere(); }

}