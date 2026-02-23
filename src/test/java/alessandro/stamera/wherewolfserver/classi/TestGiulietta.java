package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiulietta
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Giulietta(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Giulietta"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

}