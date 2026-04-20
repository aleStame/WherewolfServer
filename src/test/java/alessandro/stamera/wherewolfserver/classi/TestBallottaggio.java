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
        assertThat(isAmatoPresente()).isTrue();
    }

    @Test public void testAmatoAssente()
    {
        ballottaggio.aggiungiGiocatore("Lucia", FACTORY.getRuolo("Pazzo"));
        assertThat(isAmatoPresente()).isFalse();
    }

    private boolean isAmatoPresente() { return ballottaggio.isAmatoPresente(); }

}