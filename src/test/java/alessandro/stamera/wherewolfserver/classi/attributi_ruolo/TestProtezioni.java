package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
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
        verificaFalso(isPossedutoPresente());
        verificaFalso(isVampiroPresente());
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaLupiPresenti();
        verificaVero(isNegromantePresente());
        verificaVero(isNosferatuPresente());
        verificaVero(isPossedutoPresente());
        verificaVero(isVampiroPresente());
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

    private boolean isPossedutoPresente() { return protezioni.isPossedutoPresente(); }

    private boolean isProtezionePresente(String nome) { return protezioni.isPresente(FACTORY.getRuolo(nome)); }

    private void verificaLupiPresenti() { verificaVero(isProtezioneLupiPresente()); }

    private void verificaLupiAssenti() { verificaFalso(isProtezioneLupiPresente()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isProtezioneLupiPresente() { return protezioni.isProtezioneLupiPresente(); }

    private boolean isNegromantePresente() { return protezioni.isNegromantePresente(); }

    private boolean isNosferatuPresente() { return protezioni.isNosferatuPresente(); }

    private boolean isVampiroPresente() { return protezioni.isVampiroPresente(); }

}