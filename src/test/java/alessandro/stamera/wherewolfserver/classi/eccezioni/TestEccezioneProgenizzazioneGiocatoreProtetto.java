package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazioneGiocatoreProtetto
{

    @ParameterizedTest @CsvSource
    (
        {
            "Giulietta, 'Alessandro è Romeo.\nAvvisa il Nosferatu (Simone) della mancata progenizzazione.'",
            "Strega, 'Alessandro è protetto dalla Strega.\nAvvisa il Nosferatu (Simone) della mancata progenizzazione.'"
        }
    )
    public void testMessaggioErrore(String ruoloProtettore, String messaggio)
    {
        EccezioneProgenizzazioneGiocatoreProtetto eccezione =
            new EccezioneProgenizzazioneGiocatoreProtetto("Alessandro", ruoloProtettore, "Simone");
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}