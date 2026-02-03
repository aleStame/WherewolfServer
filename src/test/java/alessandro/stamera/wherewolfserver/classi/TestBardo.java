package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBardo
{

    @Test public void testNome() { assertThat(new Bardo().getNome()).isEqualTo("Bardo"); }

    @Test public void testBianca() { assertThat(new Bardo().getAura()).isEqualTo(BIANCA); }

}