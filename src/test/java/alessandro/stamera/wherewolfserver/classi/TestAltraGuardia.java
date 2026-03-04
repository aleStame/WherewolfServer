package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAltraGuardia
{

    @Test public void testNome() { assertThat(new AltraGuardia().getNome()).isEqualTo("Altra guardia"); }

    @Test public void testAura() { assertThat(new AltraGuardia().getAura()).isEqualTo(BIANCA); }

}