package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCriminale
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Criminale(null, null, null); }

    @Test public void tesFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isFalse(); }

}
