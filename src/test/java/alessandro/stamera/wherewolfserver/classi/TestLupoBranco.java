package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class TestLupoBranco
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new LupoBranco(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Lupo del branco"); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testCapoBranco() { assertThat(ruolo.isCapoBranco()).isFalse(); }

    @Test public void testLupoBranco() { assertThat(ruolo.isLupoBranco()).isTrue(); }

}