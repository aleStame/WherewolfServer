package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGhoul
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Ghoul(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Ghoul"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NESSUNA); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

}