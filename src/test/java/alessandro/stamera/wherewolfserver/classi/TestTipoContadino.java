package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.TipoContadino.getTipoContadino;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTipoContadino
{

    @ParameterizedTest @CsvSource
    (
        { "NORMALE, Contadino normale", "EROE, Contadino eroe", "MOSTRO, Contadino mostro", "LUPO, Contadino discendente dei lupi" }
    )
    public void testStringa(TipoContadino tipo, String descrizione) { assertThat(tipo.toString()).isEqualTo(descrizione); }

    @ParameterizedTest @CsvSource
    (
        { "Contadino normale, NORMALE", "Contadino eroe, EROE", "Contadino mostro, MOSTRO", "Contadino discendente dei lupi, LUPO" }
    )
    public void testRicerca(String descrizione, TipoContadino tipo) { assertThat(getTipoContadino(descrizione)).isEqualTo(tipo); }

}