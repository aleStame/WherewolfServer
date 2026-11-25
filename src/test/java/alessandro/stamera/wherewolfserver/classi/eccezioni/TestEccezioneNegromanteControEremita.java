package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneNegromanteControEremita
{

    @Test public void testMessaggioErrore()
    {
        String messaggio = "Sergio è l'Eremita.\nAvvisa Nicola (Negromante) che non può maledirlo, e che indicare qualcun altro";
        assertThat(new EccezioneNegromanteControEremita("Sergio", "Nicola").getMessage()).isEqualTo(messaggio);
    }

}