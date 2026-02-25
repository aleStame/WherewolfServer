package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiullare
{

    @Test public void testNome() { assertThat(new Giullare().getNome()).isEqualTo("Giullare"); }

    @Test public void testFazione() { assertThat(new Giullare().getFazione()).isEqualTo(NESSUNA); }

}