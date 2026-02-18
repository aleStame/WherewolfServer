package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBardo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Bardo(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Bardo"); }

    @Test public void testDescrizione()
    {
        verificaStringa
        (
            ruolo.getDescrizione(),
    "Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente."
        );
    }

    @Test public void testBianca() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBardo() { assertThat(ruolo.isBardo()).isTrue(); }

     @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}