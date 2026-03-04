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

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Guardia corrotta"); }

    @Test public void testDescrizione()
    {
        verificaStringa(ruolo.getDescrizione(), "La prima notte riconosce le altre guardie e in seguito gli altri criminali.");
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isTrue(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}