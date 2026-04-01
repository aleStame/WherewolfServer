package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;

public final class TestNosferatu
{

    @Test public void testNome() { assertThat(FACTORY.getRuolo("Nosferatu").getNome()).isEqualTo("Nosferatu"); }

}