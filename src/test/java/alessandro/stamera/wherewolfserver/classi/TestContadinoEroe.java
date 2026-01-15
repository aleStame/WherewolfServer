package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadinoEroe
{

    @Test public void testNome() { assertThat(new ContadinoEroe().getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(new ContadinoEroe().getFazione()).isEqualTo(VILLAGGIO); }

}