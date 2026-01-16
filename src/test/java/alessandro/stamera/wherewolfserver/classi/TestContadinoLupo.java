package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoLupo
{

    @Test public void testNome() { assertThat(new ContadinoLupo().getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(new ContadinoLupo().getFazione()).isEqualTo(VILLAGGIO); }

}