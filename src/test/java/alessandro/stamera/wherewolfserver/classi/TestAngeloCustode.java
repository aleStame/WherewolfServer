package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAngeloCustode
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new AngeloCustode(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Angelo custode"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(AMANTI); }

}