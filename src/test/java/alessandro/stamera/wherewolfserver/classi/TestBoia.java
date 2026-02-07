package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    private Boia ruolo;

    @BeforeEach public void setUp() { ruolo = new Boia(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Boia"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(INQUISIZIONE); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}