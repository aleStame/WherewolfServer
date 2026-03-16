package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.Tratto.PROTETTO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTratti
{

    private Tratti tratti;

    @BeforeEach public void setUp() { tratti = new Tratti(); }

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoPresente(Tratto tratto)
    {
        tratti.aggiungi(tratto);
        verificaVero(isPresente(tratto));
    }

    @ParameterizedTest @EnumSource(Tratto.class)
    public void testTrattoAssente(Tratto tratto) { assertThat(isPresente(tratto)).isFalse(); }

    @ParameterizedTest @CsvSource({ "CAPO_BRANCO, LUPO_BRANCO, GIOVANE_LUPO, LUPO_REIETTO, LUPO_SOLITARIO" })
    public void testCappuccettoRosso(IstanzaRuolo istanza)
    {
        tratti.aggiungiLupi();
        verificaVero(isPresente(PROTETTO));
        verificaVero(tratti.isProtezionePresente(istanza.getRuolo()));
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isPresente(Tratto tratto) { return tratti.isPresente(tratto); }

}