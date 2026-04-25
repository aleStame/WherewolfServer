package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public class Becchino extends Villaggio
{

    private Becchino()
    {
        super
        (
    "Becchino", BIANCA,
"La prima notte scopre se il Negromante è in gioco. Durante il turno del Negromante sceglie se riconoscerlo. Se lo fa, la sua fazione " +
          "diventa Negromante. Altrimenti, ogni mattino, se sono stati eliminati giocatori maledetti dal mattino precedente, il Moderatore lo " +
          "annuncia pubblicamente",
     3, false
        );
    }

    @Override public boolean isBecchino() { return true; }

    public static Ruolo getInstance() { return new Becchino(); }

}
