package alessandro.stamera.wherewolfserver.classi.ruoli;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVampiro
{

    @Test public void testNome() { assertThat(FACTORY.getRuolo("Vampiro").getNome()).isEqualTo("Vampiro"); }

}