package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneNegromanteControRomeo
{

    @Test public void testMessaggioErrore()
    {
        String messaggio = "Pierluigi non può essere maledetto perché Romeo.\nDì al Negromante di cambiare persona.";
        assertThat(new EccezioneNegromanteControRomeo("Pierluigi").getMessage()).isEqualTo(messaggio);
    }

}