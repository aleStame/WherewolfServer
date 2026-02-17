package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatoreDiVampiri
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CacciatoreDiVampiri(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Cacciatore di vampiri"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isFalse(); }

}