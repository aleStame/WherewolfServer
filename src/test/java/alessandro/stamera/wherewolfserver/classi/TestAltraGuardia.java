package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAltraGuardia
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new AltraGuardia(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Altra guardia"); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco.";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isFalse(); }

}