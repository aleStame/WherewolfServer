package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoGilda
{

    @Test public void testNome() { assertThat(new CapoGilda().getNome()).isEqualTo("Capo gilda"); }

    @Test public void testFazione() { assertThat(new CapoGilda().getFazione()).isEqualTo(CRIMINALI); }

}