package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public class TestBracconiere
{

    @Test public void testNome() { assertThat(new Bracconiere().getNome()).isEqualTo("Bracconiere"); }

    @Test public void testFazione() { assertThat(new Bracconiere().getFazione()).isEqualTo(VILLAGGIO); }

}