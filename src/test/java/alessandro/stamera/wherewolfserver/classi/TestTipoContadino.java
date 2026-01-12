package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTipoContadino
{

    @ParameterizedTest @CsvSource
    (
        { "NORMALE, Contadino normale", "EROE, Contadino eroe", "MOSTRO, Contadino mostro", "LUPO, Contadino discendente dei lupi" }
    )
    public void testStringa(TipoContadino tipo, String descrizione) { assertThat(tipo.toString()).isEqualTo(descrizione); }

}