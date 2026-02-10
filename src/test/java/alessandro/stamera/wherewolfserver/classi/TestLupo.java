package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupo
{

    private Lupo ruolo;

    @BeforeEach public void setUp() { ruolo = new Lupo(null, null, 0); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.getNumeroVoti()).isEqualTo(1);
        verificaVero(ruolo.isAccusato());
    }

    @Test public void testAmato()
    {
        verificaFalso(isAmato());
        ruolo.sceltaAngeloCustode();
        verificaVero(isAmato());
    }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isAmato() { return ruolo.isAmato(); }

}