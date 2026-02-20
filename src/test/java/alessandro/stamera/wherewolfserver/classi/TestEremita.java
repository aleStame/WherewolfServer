package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEremita
{

    @Test public void testNome() { assertThat(new Eremita().getNome()).isEqualTo("Eremita"); }

    @Test public void testAura() { assertThat(new Eremita().getAura()).isEqualTo(BIANCA); }

}