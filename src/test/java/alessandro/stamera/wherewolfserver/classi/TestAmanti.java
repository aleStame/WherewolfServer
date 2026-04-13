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

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAmanti() { assertThat(ruolo.isAmanti()).isTrue(); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}