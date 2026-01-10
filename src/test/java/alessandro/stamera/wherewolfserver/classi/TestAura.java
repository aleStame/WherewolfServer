package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAura
{

    @ParameterizedTest @CsvSource({ "BIANCA, Aura: Bianca", "NERA, Aura: Nera" })
    public void testStringaAura(Aura aura, String messaggio) { assertThat(aura.toString()).isEqualTo(messaggio); }

}