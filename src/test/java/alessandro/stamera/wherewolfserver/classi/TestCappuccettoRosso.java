package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCappuccettoRosso
{

    @Test public void testNome() { assertThat(new CappuccettoRosso().getNome()).isEqualTo("Cappuccetto rosso"); }

    @Test public void testAura() { assertThat(new CappuccettoRosso().getAura()).isEqualTo(BIANCA); }

}