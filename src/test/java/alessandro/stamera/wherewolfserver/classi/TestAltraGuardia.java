package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAltraGuardia
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new RuoliFactory().getRuolo("Altra guardia"); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Altra guardia"); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}