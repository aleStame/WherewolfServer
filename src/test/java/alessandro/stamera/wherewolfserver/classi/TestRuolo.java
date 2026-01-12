package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    @ParameterizedTest @CsvSource({ "1, 2, 3" }) public void testLune(int lune)
    {
        Ruolo ruolo = new Ruolo("Contadino", VILLAGGIO, BIANCA, lune);
        assertThat(ruolo.lune()).isEqualTo(lune);
    }

}