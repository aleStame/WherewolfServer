package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCriminale
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Criminale(null, null, null); }

    @Test public void tesFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { assertThat(ruolo.isCriminale()).isTrue(); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti(4);
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.getNumeroVoti()).isZero();
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}
