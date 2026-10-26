package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

public final class Veggente extends Villaggio
{

    private Veggente()
    {
        super
        (
    "Veggente", null, null, 0, false,
    "La Veggente è il ruolo principale della fazione degli Uomini. Il suo compito è il più delicato e, ovviamente, è in cima alla lista " +
            "dei bersagli per le Creature dell'Ombra. Scegliete bene quando rivelarvi: troppo presto e farete il gioco dei Lupi, troppo tardi e " +
            "avrete celato troppo a lungo informazioni preziose per il villaggio. Ricordatevi che è molto probabile che sarete uccisi prima che " +
            "il gioco finisca, ma la vostra sopravvivenza è del tutto ininfluente ai fini della vittoria degli Uomini. Sarà molto probabile che " +
            "ci sarà qualcun altro che si professerà Veggente, in quanto è il ruolo più efficace da bluffare se si vuole creare scompiglio e " +
            "confusione nel villaggio. Se doveste vedere che il gruppo si sta fidando di una finta Veggente potrebbe valere la pena di uscire " +
            "allo scoperto anche senza molte informazioni, in quanto seguire una falsa pista porta molto velocemente a risultati difficilmente " +
            "rimediabili in seguito."
        );
    }

    public static Ruolo getInstance() { return new Veggente(); }

}
