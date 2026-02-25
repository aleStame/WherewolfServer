package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiullare
{

    @Test public void testNome() { assertThat(new Giullare().getNome()).isEqualTo("Giullare"); }

}