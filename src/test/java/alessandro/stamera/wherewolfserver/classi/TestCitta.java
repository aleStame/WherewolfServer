package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCitta
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Citta(null, null, null); }

    @Test public void testFazione() { verificaFazione(CITTA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testCitta() { assertThat(ruolo.isCitta()).isTrue(); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti(3);
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.getNumeroVoti()).isZero();
        verificaFalso(ruolo.isAccusato());
    }

    @Test public void testGildata()
    {
        ruolo.gildata();
        verificaFazione(CRIMINALI);
    }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaFazione(Fazione fazione) { assertThat(ruolo.getFazione()).isEqualTo(fazione); }

}