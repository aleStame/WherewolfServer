package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoliFactory
{

    private RuoliFactory factory;

    @BeforeEach public void setUp() { factory = new RuoliFactory(); }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario" })
    public void testLupi(String nome) { verificaPresenza(factory.getLupi(), getRuolo(nome)); }

    @Test public void testMistici()
    {
        for(Ruolo mistico : new Ruolo[] { factory.getGoblin(), getRuolo("Guaritore"), factory.getLeprecauno() })
            verificaPresenza(factory.getMistici(), mistico);
    }

    private void verificaPresenza(Ruolo[] ruoli, Ruolo ruolo) { assertThat(ruoli).contains(ruolo); }

    private Ruolo getRuolo(String nome) { return factory.getRuolo(nome); }

}