package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAngeloCustode
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new AngeloCustode(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Angelo custode"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String soluzione =
            "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode.";
        verificaStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testAngeloCustode() { assertThat(ruolo.isAngeloCustode()).isTrue(); }

    @Test public void testSceltaAngeloCustode()
    {
        ruolo.sceltaAngeloCustode();
        verificaFalso(ruolo.isAmato());
    }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testAmato()
    {
        ruolo.sceltaAngeloCustode();
        verificaFalso(ruolo.isAmato());
    }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}