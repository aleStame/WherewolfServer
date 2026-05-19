package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Misticismo.getMisticismo;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMisticismo
{

    @ParameterizedTest @CsvSource({ "MISTICO, Mistico", "NON_MISTICO, Non mistico" })
    public void testStringaMistico(Misticismo misticismo, String messaggio) { assertThat(misticismo.toString()).isEqualTo(messaggio); }

    @ParameterizedTest @CsvSource({ "Bianca, BIANCA", "Nera, NERA" })
    public void testRicerca(String descrizione, Misticismo misticismo) { assertThat(getMisticismo(descrizione)).isEqualTo(misticismo); }

}