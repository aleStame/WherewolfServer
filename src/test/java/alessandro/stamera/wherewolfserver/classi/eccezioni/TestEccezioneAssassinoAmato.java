package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAssassinoAmato
{

    @Test public void testMessaggio()
    {
        String messaggio =
            "L'attacco dell'amato (Giuliano) da parte dell'Assassino (Antonio) causa la morte del suo Angelo custode (Stefano).\nAvvisa Stefano " +
            "dell'attacco subito.";
        assertThat(new EccezioneAssassinoAmato().getMessage()).isEqualTo(messaggio);
    }

}