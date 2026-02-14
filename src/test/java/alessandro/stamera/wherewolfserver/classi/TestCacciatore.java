package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatore
{

    @Test public void testNome() { assertThat(new Cacciatore().getNome()).isEqualTo("Cacciatore"); }

    @Test public void testAura() { assertThat(new Cacciatore().getAura()).isEqualTo(BIANCA); }
  
}