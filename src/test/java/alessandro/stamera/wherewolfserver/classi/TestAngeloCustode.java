package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAngeloCustode
{

    @Test public void testNome() { assertThat(new AngeloCustode().getNome()).isEqualTo("Angelo custode"); }

}