package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAzzeccagarbugli
{

    @Test public void testNome() { assertThat(new Azzeccagarbugli().getNome()).isEqualTo("Azzeccagarbugli"); }

    @Test public void testFazione() { assertThat(new Azzeccagarbugli().getFazione()).isEqualTo(CITTA); }

}