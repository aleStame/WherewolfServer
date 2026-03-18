package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoliFactory
{

    @Test public void testLupi()
    {
        RuoliFactory factory = new RuoliFactory();
        assertThat(factory.getLupi()).contains
        (
            factory.getRuolo("Capo branco"), factory.getRuolo("Lupo del branco"), factory.getRuolo("Giovane lupo"),
            factory.getRuolo("Lupo reietto"), factory.getRuolo("Lupo solitario")
        );
    }

    @ParameterizedTest @CsvSource({ "Goblin, Guaritore, Leprecauno" }) public void testMistici(String nome)
    {
        RuoliFactory factory = new RuoliFactory();
        assertThat(factory.getMistici()).contains(factory.getRuolo(nome));
    }

}