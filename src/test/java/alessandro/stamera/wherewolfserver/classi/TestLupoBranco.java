package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class TestLupoBranco
{

    @Test public void testNome() { assertThat(new LupoBranco().getNome()).isEqualTo("Lupo del branco"); }

}