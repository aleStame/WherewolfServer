package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    @ParameterizedTest @CsvSource({ "1, 2, 3" }) public void testLune(int lune)
    {
        Ruolo ruolo = getRuolo("Contadino", VILLAGGIO, BIANCA, lune);
        assertThat(ruolo.lune()).isEqualTo(lune);
    }

    @ParameterizedTest @CsvSource({ "BIANCA, NERA" }) public void testAura(Aura aura)
    {
        Ruolo ruolo = getRuolo("Ruolo", NESSUNA, aura, 1);
        assertThat(ruolo.aura()).isEqualTo(aura);
    }

    private Ruolo getRuolo(String nome, Fazione fazione, Aura aura, int lune) { return new Ruolo(nome, fazione, aura, lune); }

}