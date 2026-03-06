package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLadra
{

    @Test public void testNome() { assertThat(new Ladra().getNome()).isEqualTo("Ladra"); }

}