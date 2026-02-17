package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatoreDiVampiri
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CacciatoreDiVampiri(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Cacciatore di vampiri"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

}