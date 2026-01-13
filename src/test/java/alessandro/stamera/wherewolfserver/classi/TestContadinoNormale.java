package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public final class TestContadinoNormale
{

    @Test public void testAura() { assertThat(new ContadinoNormale().getAura()).isEqualTo(BIANCA); }

}