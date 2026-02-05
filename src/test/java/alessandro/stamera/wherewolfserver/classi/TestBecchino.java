package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBecchino
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Becchino(); }

    @Test public void testNome() { testStringa(ruolo.getNome(), "Becchino"); }

    @Test public void testFazione() { verificaFazione(VILLAGGIO); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String soluzione =
            "La prima notte scopre se il Negromante è in gioco. Durante il turno del Negromante sceglie se riconoscerlo. Se lo fa, la sua " +
            "fazione diventa Negromante. Altrimenti, ogni mattino, se sono stati eliminati giocatori maledetti dal mattino precedente, il " +
            "Moderatore lo annuncia pubblicamente";
        testStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testLune() { testNumero(ruolo.getLune(), 3); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBecchino() { verificaVero(ruolo.isBecchino()); }

    @Test public void testNegromante()
    {
        ruolo.riconosciNegromante();
        verificaFazione(NEGROMANTE);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        testNumero(ruolo.getNumeroVoti(), 1);
        verificaVero(ruolo.isAccusato());
    }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testSceltaAngeloCustode()
    {
        verificaFalso(ruolo.isAmato());
        ruolo.sceltaAngeloCustode();
        verificaVero(ruolo.isAmato());
    }

    private void testStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void testNumero(int valore, int soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaFazione(Fazione risultato) { assertThat(ruolo.getFazione()).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}