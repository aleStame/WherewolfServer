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
        verificaFalso(protezioni.isProtezioneLupiPresente());
        verificaFalso(protezioni.isNegromantePresente());
        protezioni.aggiungiProtezioneCreatureOmbra();
        assertThat(protezioni.isProtezioneLupiPresente()).isTrue();
        assertThat(protezioni.isNegromantePresente()).isTrue();
    }

    @Test public void testCappuccettoRosso()
    {
        verificaFalso(protezioni.isProtezioneLupiPresente());
        protezioni.aggiungiProtezioneLupi();
        assertThat(protezioni.isProtezioneLupiPresente()).isTrue();
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(int i = 0; i < FACTORY.getNumeroRuoli(); i++) verificaFalso(protezioni.isPresente(getRuolo(FACTORY.getNome(i))));
    }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}