package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoliFactory
{

    private RuoliFactory factory;

    @BeforeEach public void setUp() { factory = new RuoliFactory(); }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario" })
    public void testLupi(String nome) { verificaPresenza(factory.getLupi(), nome); }

    @ParameterizedTest @CsvSource({ "Goblin, Guaritore, Leprecauno" })
    public void testMistici(String nome) { verificaPresenza(factory.getMistici(), nome); }

    private void verificaPresenza(Ruolo[] ruoli, String nome) { assertThat(ruoli).contains(factory.getRuolo(nome)); }

}