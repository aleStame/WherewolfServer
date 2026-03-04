package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaPrincipale
{

    @Test public void testNome() { assertThat(new GuardiaPrincipale().getNome()).isEqualTo("Guardia"); }

    @Test public void testAura() { assertThat(new GuardiaPrincipale().getAura()).isEqualTo(BIANCA); }

}