package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCappuccettoRosso
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CappuccettoRosso(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Cappuccetto rosso"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

}