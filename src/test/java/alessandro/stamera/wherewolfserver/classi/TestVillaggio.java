package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVillaggio
{

    @Test public void testFazione()
    {
        assertThat(new Villaggio(null, null, null, -1, true).getFazione()).isEqualTo(VILLAGGIO);
    }

}