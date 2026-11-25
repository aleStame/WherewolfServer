package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoPossedutoNonRiuscito
{

    @ParameterizedTest @CsvSource
    (
        {
            "Strega, 'Paolo (Vampiro) è protetto dalla Strega.\nAvvisa il Posseduto (Luca) che non può possederlo.'",
            "Giulietta, 'Paolo (Vampiro) è Romeo.\nAvvisa il Posseduto (Luca) che non può possederlo.'"
        }
    )
    public void testMessaggioErrore(String nomeRuoloProtettore, String messaggio)
    {
        EccezioneAttaccoPossedutoNonRiuscito eccezione =
            new EccezioneAttaccoPossedutoNonRiuscito(nomeRuoloProtettore, "Paolo", "Vampiro", "Luca");
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}