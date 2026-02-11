package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    @Test public void testInizializzazione()
    {
        Ruolo ruolo = new Ruolo(null, null, null, null, -1, false);
        assertThat(ruolo.getNumeroVoti()).isZero();
        verificaFalso(ruolo.isAmato());
        verificaFalso(ruolo.isAccusato());
    }

    @Test public void testAccusato()
    {
        Ruolo ruolo = new Ruolo(null, null, null, null, -1, false);
        verificaFalso(ruolo.isAccusato());
        ruolo.accusa();
        assertThat(ruolo.isAccusato()).isTrue();
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}