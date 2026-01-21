package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoccaDiRosa
{

    @Test public void testNome() { assertThat(new BoccaDiRosa().getNome()).isEqualTo("Bocca di rosa"); }

}