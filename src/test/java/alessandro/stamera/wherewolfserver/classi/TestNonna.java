package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestNonna
{

    @Test public void testNome() { assertThat(FACTORY.getRuolo("Nonna").getNome()).isEqualTo("Nonna"); }

    @Test public void testAura() { assertThat(FACTORY.getRuolo("Nonna").getAura()).isEqualTo(BIANCA); }

}