package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoMostro
{

    @Test public void testAura() { assertThat(new ContadinoMostro().getAura()).isEqualTo(NERA); }

    @Test public void testContadinoNormale() { assertThat(new ContadinoNormale().isContadinoNormale()).isFalse(); }

}