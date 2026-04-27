package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriEliminati
{

    @Test public void testBardoPresente()
    {
        GiocatoriEliminati giocatori = new GiocatoriEliminati();
        giocatori.aggiungiGiocatore("Pino", FACTORY.getRuolo("Bardo"));
        assertThat(giocatori.isBardoPresente()).isTrue();
    }

}