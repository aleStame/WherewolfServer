package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGhoul
{

    @Test public void testNome() { assertThat(new Ghoul().getNome()).isEqualTo("Ghoul"); }

    @Test public void testFazione() { assertThat(new Ghoul().getFazione()).isEqualTo(NESSUNA); }

}