package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEsitoControlloSensitiva
{

    @ParameterizedTest @CsvSource({ "VILLAGGIO, Villaggio", "NON_VILLAGGIO, Non villaggio" })
    public void testStringaSensitiva(EsitoControlloSensitiva esito, String messaggio) { assertThat(esito.toString()).isEqualTo(messaggio); }

}