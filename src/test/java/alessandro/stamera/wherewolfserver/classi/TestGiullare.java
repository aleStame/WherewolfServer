package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NESSUNA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiullare
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Giullare(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Giullare"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NESSUNA); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

}