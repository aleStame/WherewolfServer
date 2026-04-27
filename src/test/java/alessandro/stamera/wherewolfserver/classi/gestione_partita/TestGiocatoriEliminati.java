package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriEliminati
{

    private GiocatoriEliminati giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriEliminati(); }

    @Test public void testBardoPresente()
    {
        giocatori.aggiungiGiocatore("Pino", FACTORY.getRuolo("Bardo"));
        assertThat(giocatori.isBardoPresente()).isTrue();
    }

    @Test public void testBardoAssente() { assertThat(giocatori.isBardoPresente()).isFalse(); }

}