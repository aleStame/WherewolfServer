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
        verificaFalso(isProtezioneLupiPresente());
        verificaFalso(isNegromantePresente());
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaLupiPresenti();
        verificaVero(isNegromantePresente());
    }

    @Test public void testCappuccettoRosso()
    {
        verificaFalso(isProtezioneLupiPresente());
        protezioni.aggiungiProtezioneLupi();
        verificaLupiPresenti();
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(int i = 0; i < FACTORY.getNumeroRuoli(); i++) verificaFalso(protezioni.isPresente(getRuolo(FACTORY.getNome(i))));
    }

    private void verificaLupiPresenti() { verificaVero(isProtezioneLupiPresente()); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return protezioni.isProtezioneLupiPresente(); }

    private boolean isNegromantePresente() { return protezioni.isNegromantePresente(); }

}