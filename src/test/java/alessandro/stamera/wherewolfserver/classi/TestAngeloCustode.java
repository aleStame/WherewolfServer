package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAngeloCustode
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new AngeloCustode(); }

    @Test public void testNome() { testStringa(ruolo.getNome(), "Angelo custode"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(AMANTI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String soluzione =
            "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode.";
        testStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    private void testStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

}