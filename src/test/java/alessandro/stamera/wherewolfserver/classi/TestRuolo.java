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
        verificaFalso(isAccusato());
    }

    @Test public void testAccusato()
    {
        verificaFalso(isAccusato());
        ruolo.accusa();
        assertThat(isAccusato()).isTrue();
    }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}