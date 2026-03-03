package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardia
{

    @Test public void testFazione() { assertThat(new Guardia(null).getFazione()).isEqualTo(VILLAGGIO); }

}