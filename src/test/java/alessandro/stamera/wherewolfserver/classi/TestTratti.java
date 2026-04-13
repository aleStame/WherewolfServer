package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
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

    @ParameterizedTest @EnumSource(Tratto.class) public void testTrattoAssente(Tratto tratto) { verificaFalso(isPresente(tratto)); }

    @Test public void testCappuccettoRosso()
    {
        verificaLupiAssenti();
        tratti.aggiungiProtezioneLupi();
        verificaLupiPresenti();
        verificaProtetto();
    }

    @Test public void testMaledizione()
    {
        verificaFalso(isMaledetto());
        verificaVero(tratti.maledizione());
        verificaVero(isMaledetto());
    }

    @Test public void testCreatureOmbra()
    {
        verificaLupiAssenti();
        verificaFalso(isProtezioneNegromantePresente());
        verificaFalso(isProtezioneNosferatuPresente());
        tratti.aggiungiProtezioneCreatureOmbra();
        verificaLupiPresenti();
        verificaVero(isProtezioneNegromantePresente());
        verificaVero(isProtezioneNosferatuPresente());
        verificaProtetto();
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Mago, Megera, Negromante" }) public void testProtezioneMistici(String nome)
    {
        tratti.aggiungiProtezioneMistici();
        verificaVero(tratti.isProtezionePresente(FACTORY.getRuolo(nome)));
        verificaProtetto();
    }

    private void verificaProtetto() { verificaTrattoPresente(PROTETTO); }

    private boolean isMaledetto() { return tratti.isMaledetto(); }

    private boolean isProtezioneNosferatuPresente() { return tratti.isProtezioneNosferatuPresente(); }

    private void verificaTrattoPresente(Tratto tratto) { verificaVero(isPresente(tratto)); }

    private boolean isPresente(Tratto tratto) { return tratti.isPresente(tratto); }

    private void verificaLupiPresenti() { verificaVero(isProtezioneLupiPresente()); }

    private void verificaLupiAssenti() { verificaFalso(isProtezioneLupiPresente()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return tratti.isProtezioneLupiPresente(); }

    private boolean isProtezioneNegromantePresente() { return tratti.isProtezioneNegromantePresente(); }

}