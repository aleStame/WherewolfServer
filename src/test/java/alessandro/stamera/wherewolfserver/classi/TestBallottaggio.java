package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBallottaggio
{

    private Ballottaggio ballottaggio;

    @BeforeEach public void setUp() { ballottaggio = new Ballottaggio(); }

    @Test public void testAmatoPresente()
    {
        Ruolo ruolo = FACTORY.getRuolo("Pazzo");
        ruolo.sceltaAngeloCustode();
        ballottaggio.aggiungiGiocatore("Gabriella", ruolo);
        assertThat(ballottaggio.isAmatoPresente()).isTrue();
    }

    @Test public void testAmatoAssente()
    {
        ballottaggio.aggiungiGiocatore("Gabriella", FACTORY.getRuolo("Pazzo"));
        assertThat(ballottaggio.isAmatoPresente()).isFalse();
    }

}