package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAzzeccagarbugli
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Azzeccagarbugli(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Azzeccagarbugli"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CITTA); }

}