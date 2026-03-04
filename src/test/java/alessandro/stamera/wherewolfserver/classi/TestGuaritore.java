package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuaritore
{

    @Test public void testNome() { assertThat(new Guaritore().getNome()).isEqualTo("Guaritore"); }

    @Test public void testAura() { assertThat(new Guaritore().getAura()).isEqualTo(BIANCA); }

}