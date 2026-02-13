package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestBracconiere
{

    @Test public void testNome() { assertThat(new Bracconiere().getNome()).isEqualTo("Bracconiere"); }

}