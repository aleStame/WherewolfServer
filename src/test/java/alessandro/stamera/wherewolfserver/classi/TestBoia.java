package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    @Test public void testNome() { assertThat(new Boia().getNome()).isEqualTo("Boia"); }

    @Test public void testFazione() { assertThat(new Boia().getFazione()).isEqualTo(INQUISIZIONE); }

}