package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneGildata
{

    @Test public void testGildataFallita()
    {
        assertThat(new EccezioneGildata("Salvo").getMessage()).isEqualTo("Impossibile criminalizzare Salvo.");
    }

}