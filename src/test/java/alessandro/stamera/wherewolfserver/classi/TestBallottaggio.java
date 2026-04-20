package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBallottaggio
{

    @Test public void testAmatoPresente()
    {
        Ruolo ruolo = FACTORY.getRuolo("Pazzo");
        Ballottaggio ballottaggio = new Ballottaggio();
        ruolo.sceltaAngeloCustode();
        ballottaggio.aggiungiGiocatore("Gabriella", ruolo);
        assertThat(ballottaggio.isAmatoPresente()).isTrue();
    }

}