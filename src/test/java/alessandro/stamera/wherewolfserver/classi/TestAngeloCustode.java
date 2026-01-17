package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAngeloCustode
{

    @Test public void testNome() { assertThat(new AngeloCustode().getNome()).isEqualTo("Angelo custode"); }

    @Test public void testFazione() { assertThat(new AngeloCustode().getFazione()).isEqualTo(AMANTI); }

}