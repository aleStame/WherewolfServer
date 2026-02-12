package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBorgomastro
{

    @Test public void testNome() { assertThat(new Borgomastro().getNome()).isEqualTo("Borgomastro"); }

    @Test public void testAura() { assertThat(new Borgomastro().getAura()).isEqualTo(BIANCA); }

}