package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatore
{

    @Test public void testNome() { assertThat(new Cacciatore().getNome()).isEqualTo("Cacciatore"); }
  
}