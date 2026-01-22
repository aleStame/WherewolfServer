package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoGilda
{

    @Test public void testNome() { assertThat(new CapoGilda().getNome()).isEqualTo("Capo gilda"); }

}