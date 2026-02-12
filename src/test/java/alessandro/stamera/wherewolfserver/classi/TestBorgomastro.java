package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBorgomastro
{

    @Test public void testNome() { assertThat(new Borgomastro().getNome()).isEqualTo("Borgomastro"); }

}