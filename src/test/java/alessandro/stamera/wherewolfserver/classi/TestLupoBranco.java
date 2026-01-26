package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class TestLupoBranco
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new LupoBranco(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Lupo del branco"); }

    @Test public void testDescrizione()
    {
        String soluzione =
            "La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore che verrà " +
            "ucciso se è il lupo più potente in gioco.";
        verificaStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testCapoBranco() { assertThat(ruolo.isCapoBranco()).isFalse(); }

    @Test public void testLupoBranco() { assertThat(ruolo.isLupoBranco()).isTrue(); }

    @Test public void testGiovaneLupo() { assertThat(ruolo.isGiovaneLupo()).isFalse(); }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

}