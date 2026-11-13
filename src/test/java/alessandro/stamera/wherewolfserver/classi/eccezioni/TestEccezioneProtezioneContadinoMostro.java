package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProtezioneContadinoMostro
{

    @Test public void testMessaggioErrore()
    {
        String messaggio = "La Strega (Agatha) ha cercato di proteggere il Contadino Mostro (Wanda).\nAvvisa Agatha della sua morte.";
        assertThat(new EccezioneProtezioneContadinoMostro("Agatha", "Wanda").getMessage()).isEqualTo(messaggio);
    }

}