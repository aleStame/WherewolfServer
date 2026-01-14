package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoMostro
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new ContadinoMostro(); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testContadino() { assertThat(ruolo.isContadino()).isTrue(); }

    @Test public void testContadinoNormale() { assertThat(ruolo.isContadinoNormale()).isFalse(); }

    @Test public void testContadinoMostro() { assertThat(ruolo.isContadinoMostro()).isTrue(); }

}