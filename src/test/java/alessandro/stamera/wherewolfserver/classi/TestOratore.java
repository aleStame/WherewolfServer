package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestOratore
{

    private static final String NOME = "Oratore";

    @Test public void testNome() { assertThat(FACTORY.getRuolo(NOME).getNome()).isEqualTo(NOME); }

}