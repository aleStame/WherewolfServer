package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaLupiPresenti();
        verificaVero(isNegromantePresente());
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
        for(int i = 0; i < FACTORY.getNumeroRuoli(); i++) verificaFalso(protezioni.isPresente(FACTORY.getRuolo(FACTORY.getNome(i))));
    }

    private void verificaLupiPresenti() { verificaVero(isProtezioneLupiPresente()); }

    private void verificaLupiAssenti() { verificaFalso(isProtezioneLupiPresente()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return protezioni.isProtezioneLupiPresente(); }

    private boolean isNegromantePresente() { return protezioni.isNegromantePresente(); }

}