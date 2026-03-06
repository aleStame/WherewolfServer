package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import alessandro.stamera.wherewolfserver.classi.Fazione;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLeprecauno
{

    @Test public void testNome() { assertThat(new Leprecauno().getNome()).isEqualTo("Leprecauno"); }

    @Test public void testFazione() { assertThat(new Leprecauno().getFazione()).isEqualTo(Fazione.NESSUNA); }

}