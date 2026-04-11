package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPosseduto
{

    @Test public void testNome() { assertThat(FACTORY.getRuolo("Posseduto").getNome()).isEqualTo("Posseduto"); }

}