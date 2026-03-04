package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaCorrotta
{

    @Test public void testNome() { assertThat(new GuardiaCorrotta().getNome()).isEqualTo("Guardia corrotta"); }

}