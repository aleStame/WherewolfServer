package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGhoul
{

    @Test public void testNome() { assertThat(new Ghoul().getNome()).isEqualTo("Ghoul"); }

}