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

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}