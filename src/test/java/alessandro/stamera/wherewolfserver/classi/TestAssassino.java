package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAssassino
{

    @Test public void testNome() { assertThat(new Assassino().getNome()).isEqualTo("Assassino"); }

    @Test public void testAura() { assertThat(new Assassino().getAura()).isEqualTo(BIANCA); }

}