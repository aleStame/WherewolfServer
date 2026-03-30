package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        verificaTrattoPresente(tratto);
    }

    @ParameterizedTest @EnumSource(Tratto.class)
    public void testTrattoAssente(Tratto tratto) { assertThat(isPresente(tratto)).isFalse(); }

    @Test public void testCappuccettoRosso()
    {
        assertThat(tratti.isProtezioneLupiPresente()).isFalse();
        tratti.aggiungiProtezioneLupi();
        verificaVero(tratti.isProtezioneLupiPresente());
    }

    @Test public void testMaledizione()
    {
        assertThat(isMaledetto()).isFalse();
        tratti.maledizione();
        verificaVero(isMaledetto());
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Giovane lupo, Lupo reietto, Lupo solitario" })
    public void testCreatureOmbra(String nome)
    {
        tratti.aggiungiProtezioneCreatureOmbra();
        verificaProtezione(nome);
    }

    private boolean isMaledetto() { return tratti.isMaledetto(); }

    private void verificaProtezione(String nome)
    {
        verificaTrattoPresente(PROTETTO);
        verificaVero(tratti.isProtezionePresente(new RuoliFactory().getRuolo(nome)));
    }

    private void verificaTrattoPresente(Tratto tratto) { verificaVero(isPresente(tratto)); }

    private boolean isPresente(Tratto tratto) { return tratti.isPresente(tratto); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}