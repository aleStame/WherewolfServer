package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    @Test public void testNome() { assertThat(new Boia().getNome()).isEqualTo("Boia"); }

}