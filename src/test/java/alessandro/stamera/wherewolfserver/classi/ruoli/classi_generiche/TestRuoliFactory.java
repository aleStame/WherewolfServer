package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoliFactory
{

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario", "Contadino discendente dei lupi" })
    public void testLupi(String nome) { verificaPresenza(FACTORY.getLupi(), getRuolo(nome)); }

    @ParameterizedTest @CsvSource({ "Guaritore", "Mago", "Megera", "Negromante" })
    public void testMistici(String nome) { verificaPresenza(FACTORY.getMistici(), getRuolo(nome)); }

    @ParameterizedTest @CsvSource
    (
        { "Capo branco, Contadino discendente dei lupi, Giovane lupo, Lupo del branco, Lupo reietto, Lupo solitario, Negromante, Posseduto" }
    )
    public void testCreatureOmbra(String nome) { verificaPresenza(FACTORY.getCreatureOmbra(), getRuolo(nome)); }

    private void verificaPresenza(Ruolo[] ruoli, Ruolo ruolo) { assertThat(ruoli).contains(ruolo); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}