package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoliFactory
{

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario" })
    public void testLupi(String nome) { verificaPresenza(FACTORY.getLupi(), getRuolo(nome)); }

    @Test public void testMistici()
    {
        for(Ruolo mistico : new Ruolo[] { getRuolo("Goblin"), getRuolo("Guaritore"), getRuolo("Leprecauno") })
            verificaPresenza(FACTORY.getMistici(), mistico);
    }

    private void verificaPresenza(Ruolo[] ruoli, Ruolo ruolo) { assertThat(ruoli).contains(ruolo); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}