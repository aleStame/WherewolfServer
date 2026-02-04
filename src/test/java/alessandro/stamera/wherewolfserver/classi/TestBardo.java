package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBardo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Bardo(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Bardo"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testBianca() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isCapoGilda()); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}