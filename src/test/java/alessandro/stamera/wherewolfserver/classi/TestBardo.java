package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBardo
{

    @Test public void testNome() { assertThat(new Bardo().getNome()).isEqualTo("Bardo"); }

}