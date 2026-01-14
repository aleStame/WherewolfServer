package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoMostro
{

    private Contadino contadino;

    @BeforeEach public void setUp() { contadino = new ContadinoMostro(); }

    @Test public void testAura() { assertThat(contadino.getAura()).isEqualTo(NERA); }

    @Test public void testContadinoNormale() { assertThat(contadino.isContadinoNormale()).isFalse(); }

}