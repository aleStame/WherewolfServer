package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.getAura;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAura
{

    @ParameterizedTest @CsvSource({ "BIANCA, Bianca", "NERA, Nera" })
    public void testStringaAura(Aura aura, String messaggio) { assertThat(aura.toString()).isEqualTo(messaggio); }
    
    @ParameterizedTest @CsvSource({ "Bianca, BIANCA", "Nera, NERA" })
    public void testRicerca(String descrizione, Aura aura) { assertThat(getAura(descrizione)).isEqualTo(aura); }

}