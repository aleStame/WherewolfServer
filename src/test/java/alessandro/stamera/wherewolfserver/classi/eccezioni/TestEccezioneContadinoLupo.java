package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneContadinoLupo
{

    @ParameterizedTest @CsvSource
    (
        {
            "'Lupi del branco', 'Il Contadino discendente dei lupi (Mario) è stato attaccato dai Lupi del branco, pertanto adesso fa parte della " +
            "loro fazione.\nSveglia Mario e fagli riconoscere gli altri lupi.'",
            "'Lupo solitario', 'Il Contadino discendente dei lupi (Mario) è stato attaccato dal Lupo solitario, pertanto anche lui diventa tale.\n" +
            "Sveglia Mario e fagli riconoscere l'altro Lupo solitario.'"
        }
    )
    public void testMessaggioErrore(String fazione, String messaggio)
    {
        assertThat(new EccezioneContadinoLupo("Mario", fazione).getMessage()).isEqualTo(messaggio);
    }

}