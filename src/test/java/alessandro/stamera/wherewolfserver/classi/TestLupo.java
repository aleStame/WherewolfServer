package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupo
{

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testFazione(int lune) { assertThat(getRuoloEsempio(lune).getFazione()).isEqualTo(LUPO_BRANCO); }

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testAura(int lune) { assertThat(getRuoloEsempio(lune).getAura()).isEqualTo(NERA); }

    @ParameterizedTest @CsvSource({ "1, 2, 3" })
    public void testGildata(int lune)
    {
        Ruolo ruolo = getRuoloEsempio(lune);
        assertThat(ruolo.gildata()).isFalse();
        assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO);
    }

    private Ruolo getRuoloEsempio(int lune) { return new Lupo("Ruolo", "Descrizione generica", lune); }

}