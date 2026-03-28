package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestNegromante
{

    private static final String NOME = "Negromante";

    @Test public void testNome() { assertThat(FACTORY.getRuolo(NOME).getNome()).isEqualTo(NOME); }

    @Test public void testFazione() { assertThat(FACTORY.getRuolo(NOME).getFazione()).isEqualTo(NEGROMANTE); }

    @Test public void testAura() { assertThat(FACTORY.getRuolo(NOME).getAura()).isEqualTo(BIANCA); }

}