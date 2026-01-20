package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBecchino
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Becchino(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Becchino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

}