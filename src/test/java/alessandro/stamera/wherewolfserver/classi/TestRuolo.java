package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Ruolo(null, null, null, null, -1, false); }

    @Test public void testInizializzazione()
    {
        verificaNessunVoto();
        verificaFalso(ruolo.isAmato());
        verificaFalso(isAccusato());
    }

    @Test public void testAccusato()
    {
        verificaFalso(isAccusato());
        ruolo.accusa();
        assertThat(isAccusato()).isTrue();
    }

    @Test public void testVoti()
    {
        ruolo.incrementaVoti();
        assertThat(getNumeroVoti()).isEqualTo(1);
        ruolo.annullaVoti();
        verificaNessunVoto();
    }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}