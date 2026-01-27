package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoReietto
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new LupoReietto(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Lupo reietto"); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testCapoBranco() { assertThat(ruolo.isCapoBranco()).isFalse(); }

    @Test public void testLupoBranco() { assertThat(ruolo.isLupoBranco()).isFalse(); }

}