package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiullare
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Giullare(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Giullare"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NESSUNA); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { assertThat(ruolo.isGiullare()).isTrue(); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}