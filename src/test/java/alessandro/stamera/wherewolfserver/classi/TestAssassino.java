package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAssassino
{

    @Test public void testNome() { assertThat(new Assassino().getNome()).isEqualTo("Assassino"); }

}