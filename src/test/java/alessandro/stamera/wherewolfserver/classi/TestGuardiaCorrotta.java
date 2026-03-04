package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaCorrotta
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new GuardiaCorrotta(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Guardia corrotta"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

}