package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.LUPO_BRANCO;

public final class TestLupoBranco
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = LUPO_BRANCO.getRuolo(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Lupo del branco"); }

    @Test public void testDescrizione()
    {
        String soluzione =
            "La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore che verrà " +
            "ucciso se è il lupo più potente in gioco.";
        verificaStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaVero(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaFalso(ruolo.isLupoReietto()); }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}