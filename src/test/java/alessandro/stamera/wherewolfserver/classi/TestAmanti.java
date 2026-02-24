package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;

public final class TestAmanti
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Amanti(null, null); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(AMANTI); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testAmanti() { assertThat(ruolo.isAmanti()).isTrue(); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}