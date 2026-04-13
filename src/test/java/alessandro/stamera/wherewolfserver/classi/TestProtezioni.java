package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestProtezioni
{

    private Protezioni protezioni;

    @BeforeEach public void setUp() { protezioni = new Protezioni(); }

    @Test public void testCreatureOmbra()
    {
        verificaLupiAssenti();
        verificaFalso(isNegromantePresente());
        verificaFalso(isNosferatuPresente());
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaLupiPresenti();
        verificaVero(isNegromantePresente());
        verificaVero(isNosferatuPresente());
    }

    @Test public void testCappuccettoRosso()
    {
        verificaLupiAssenti();
        protezioni.aggiungiProtezioneLupi();
        verificaLupiPresenti();
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(int i = 0; i < FACTORY.getNumeroRuoli(); i++) verificaFalso(isProtezionePresente(FACTORY.getNome(i)));
    }

    @ParameterizedTest @CsvSource({ "Guaritore, Mago, Megera, Negromante" }) public void testProtezioneMistici(String nome)
    {
        protezioni.aggiungiProtezioneMistici();
        verificaVero(isProtezionePresente(nome));
    }

    private boolean isProtezionePresente(String nome) { return protezioni.isPresente(FACTORY.getRuolo(nome)); }

    private void verificaLupiPresenti() { verificaVero(isProtezioneLupiPresente()); }

    private void verificaLupiAssenti() { verificaFalso(isProtezioneLupiPresente()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return protezioni.isProtezioneLupiPresente(); }

    private boolean isNegromantePresente() { return protezioni.isNegromantePresente(); }

    private boolean isNosferatuPresente() { return protezioni.isNosferatuPresente(); }

}