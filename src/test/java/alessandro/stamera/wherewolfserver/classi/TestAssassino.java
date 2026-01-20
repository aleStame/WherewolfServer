package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAssassino
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Assassino(); }

    @Test public void testNome() { testStringa(ruolo.getNome(), "Assassino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void tesDescrizione()
    {
        String soluzione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
            "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.";
        testStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    private void testStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

}