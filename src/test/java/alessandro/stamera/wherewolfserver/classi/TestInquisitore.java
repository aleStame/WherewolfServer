package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisitore
{

    @Test public void testNome() { assertThat(new Inquisitore().getNome()).isEqualTo("Inquisitore"); }

}