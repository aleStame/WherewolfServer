package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestProtezioni
{

    private Protezioni protezioni;

    @BeforeEach public void setUp()
    {
        protezioni = new Protezioni();
    }

    @ParameterizedTest @CsvSource({ "CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO, LUPO_REIETTO, GIOVANE_LUPO" })
    public void testCreatureOmbra(IstanzaRuolo istanza)
    {
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaPresenza(istanza);
    }

    @ParameterizedTest @CsvSource({ "CAPO_BRANCO, LUPO_BRANCO, LUPO_SOLITARIO, LUPO_REIETTO, GIOVANE_LUPO" })
    public void testCappuccettoRosso(IstanzaRuolo istanza)
    {
        protezioni.aggiungiProtezioneLupi();
        verificaPresenza(istanza);
    }

    @ParameterizedTest @EnumSource(IstanzaRuolo.class) public void testPerdiProtezioni(IstanzaRuolo istanza)
    {
        protezioni.perdiProtezioni();
        assertThat(isPresente(istanza)).isFalse();
    }

    @ParameterizedTest @CsvSource({ "GUARITORE, GOBLIN, LEPRECAUNO" }) public void testProtezioneMistici(IstanzaRuolo istanza)
    {
        protezioni.aggiungiProtezioneMistici();
        verificaPresenza(istanza);
    }

    private void verificaPresenza(IstanzaRuolo istanza) { assertThat(isPresente(istanza)).isTrue(); }

    private boolean isPresente(IstanzaRuolo istanza) { return protezioni.isPresente(istanza.getRuolo()); }

}