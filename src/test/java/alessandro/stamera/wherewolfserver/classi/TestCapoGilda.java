package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoGilda
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CapoGilda(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Capo gilda"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

}