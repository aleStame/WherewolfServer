package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadino
{

    @Test public void testNome() { assertThat(new Contadino(BIANCA).getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(new Contadino(BIANCA).getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testLune() { assertThat(new Contadino(BIANCA).getLune()).isEqualTo(1); }

}