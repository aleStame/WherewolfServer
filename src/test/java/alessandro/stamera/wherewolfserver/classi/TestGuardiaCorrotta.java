package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaCorrotta
{

    @Test public void testNome() { assertThat(new GuardiaCorrotta().getNome()).isEqualTo("Guardia corrotta"); }

    @Test public void testFazione() { assertThat(new GuardiaCorrotta().getFazione()).isEqualTo(CRIMINALI); }

}