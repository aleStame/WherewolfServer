package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoBranco
{

    @Test public void testNome() { assertThat(new CapoBranco().getNome()).isEqualTo("Capo branco"); }

    @Test public void testLune() { assertThat(new CapoBranco().getLune()).isEqualTo(1); }

}