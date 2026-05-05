package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Bracconiere extends Villaggio
{

    private final Potere potere;

    private Bracconiere()
    {
        super
        (
    "Bracconiere", BIANCA,
"La prima notte scopre quanti lupi del branco sono in gioco e se è in gioco il Lupo solitario. Durante il turno dei lupi mannari può " +
          "segnalare la sua presenza: se in gioco è rimasto soltanto un lupo mannaro, questi non può attaccare",
     3, false
        );
        potere = new Potere();
    }

    @Override public boolean isBracconiere() { return true; }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    public static Ruolo getInstance() { return new Bracconiere(); }

}