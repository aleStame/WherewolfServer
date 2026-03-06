package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLadra
{

    @Test public void testNome() { assertThat(new Ladra().getNome()).isEqualTo("Ladra"); }

    @Test public void testAura() { assertThat(new Ladra().getAura()).isEqualTo(BIANCA); }

}