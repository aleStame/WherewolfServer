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

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.getNumeroVoti()).isZero();
        verificaFalso(ruolo.isAccusato());
    }

    @Test public void testUtilizzoPotere()
    {
        verificaFalso(ruolo.isPotereUtilizzato());
        ruolo.utilizzaPotere();
        assertThat(ruolo.isPotereUtilizzato()).isTrue();
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}
