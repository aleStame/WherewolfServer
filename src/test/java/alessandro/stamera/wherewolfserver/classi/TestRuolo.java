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
        assertThat(ruolo.getNumeroVoti()).isZero();
        verificaFalso(ruolo.isAmato());
        verificaFalso(ruolo.isAccusato());
    }

    @Test public void testAccusato()
    {
        verificaFalso(ruolo.isAccusato());
        ruolo.accusa();
        assertThat(ruolo.isAccusato()).isTrue();
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}