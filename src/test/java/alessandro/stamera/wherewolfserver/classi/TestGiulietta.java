package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiulietta
{

    @Test public void testNome() { assertThat(new Giulietta().getNome()).isEqualTo("Giulietta"); }

    @Test public void testAura() { assertThat(new Giulietta().getAura()).isEqualTo(BIANCA); }

}