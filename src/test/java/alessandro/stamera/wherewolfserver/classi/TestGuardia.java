package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardia
{

    @Test public void testNome() { assertThat(new Guardia().getNome()).isEqualTo("Guardia"); }

}