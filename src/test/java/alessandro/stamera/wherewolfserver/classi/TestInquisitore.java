package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisitore
{

    @Test public void testNome() { assertThat(new Inquisitore().getNome()).isEqualTo("Inquisitore"); }

    @Test public void testAura() { assertThat(new Inquisitore().getAura()).isEqualTo(BIANCA); }

}