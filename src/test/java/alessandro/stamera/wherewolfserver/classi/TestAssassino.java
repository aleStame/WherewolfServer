package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAssassino
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Assassino(); }

    @Test public void testNome() { testStringa(ruolo.getNome(), "Assassino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void tesDescrizione()
    {
        String soluzione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
            "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.";
        testStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testAssassino()
    {
        verificaVero(ruolo.isAssassino());
        ruolo.utilizzaPotere();
        verificaVero(ruolo.isPotereUtilizzato());
    }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.getNumeroVoti()).isZero();
        verificaFalso(ruolo.isAccusato());
    }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testCustodiaAngelo()
    {
        verificaFalso(isAmato());
        ruolo.sceltaAngeloCustode();
        verificaVero(isAmato());
    }

    private void testStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isAmato() { return ruolo.isAmato(); }

}