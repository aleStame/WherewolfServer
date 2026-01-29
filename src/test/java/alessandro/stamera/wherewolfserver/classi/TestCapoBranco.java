package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoBranco
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CapoBranco(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Capo branco"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore, e questi " +
            "viene ucciso.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testCapoBranco() { assertThat(ruolo.isCapoBranco()).isTrue(); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaFalso(ruolo.isLupoReietto()); }

    private void verificaStringa(String valore, String descrizione) { assertThat(valore).isEqualTo(descrizione); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}