package alessandro.stamera.wherewolfserver.classi.ruoli;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVeggente
{

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "La Veggente è il ruolo principale della fazione degli Uomini. Il suo compito è il più delicato e, ovviamente, è in cima alla lista " +
            "dei bersagli per le Creature dell'Ombra. Scegliete bene quando rivelarvi: troppo presto e farete il gioco dei Lupi, troppo tardi e " +
            "avrete celato troppo a lungo informazioni preziose per il villaggio. Ricordatevi che è molto probabile che sarete uccisi prima che " +
            "il gioco finisca, ma la vostra sopravvivenza è del tutto ininfluente ai fini della vittoria degli Uomini. Sarà molto probabile che " +
            "ci sarà qualcun altro che si professerà Veggente, in quanto è il ruolo più efficace da bluffare se si vuole creare scompiglio e " +
            "confusione nel villaggio. Se doveste vedere che il gruppo si sta fidando di una finta Veggente potrebbe valere la pena di uscire " +
            "allo scoperto anche senza molte informazioni, in quanto seguire una falsa pista porta molto velocemente a risultati difficilmente " +
            "rimediabili in seguito.";
        assertThat(FACTORY.getRuolo("Veggente").getConsigli()).isEqualTo(messaggio);
    }

}