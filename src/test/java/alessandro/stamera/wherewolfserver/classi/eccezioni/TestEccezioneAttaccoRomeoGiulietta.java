package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoRomeoGiulietta
{

    @Test public void testMessaggioErrore()
    {
        String messaggio = "Hal è Romeo.\nAvvisa anche John (Giulietta) del fatto che deve morire.";
        assertThat(new EccezioneAttaccoRomeoGiulietta("Hal", "John").getMessage()).isEqualTo(messaggio);
    }

}