package alessandro.stamera.wherewolfserver.classi.ruoli;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestViaggiatore
{

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "La vostra condizione di vittoria è unica e differente da tutte le altre: dovete rimanere vivo. Se non fino alla fine del gioco, " +
            "almeno quanto basta da poter lasciare il villaggio. La scelta più importante sarà quella di decidere se rivelare o meno la vostra " +
            "identità al villaggio. Quello del Viaggiatore è uno dei ruoli verso cui le reazioni saranno più disparate: a volte vi crederanno e vi " +
            "trascureranno del tutto, a volte vi bruceranno subito poiché, non avendo schieramento, potreste comunque essere d’intralcio. Spesso " +
            "potrebbe risultare più efficace mentire sulla vostra identità, fingendo di essere un ruolo che nessuno ha interesse ad eliminare in " +
            "quel momento. Dovete solo capire quale può essere quel ruolo in quel contesto specifico.";
        assertThat(FACTORY.getRuolo("Viaggiatore").getConsigli()).isEqualTo(messaggio);
    }

    @Test public void testControlloMedium() { assertThat(FACTORY.getRuolo("Viaggiatore").controlloMedium()).isEqualTo(BIANCA); }

}