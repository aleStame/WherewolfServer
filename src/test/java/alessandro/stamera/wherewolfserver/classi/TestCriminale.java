package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCriminale
{

    @Test
    public void tesFazione() { assertThat(new Criminale(null, null, null).getFazione()).isEqualTo(CRIMINALI); }

}
