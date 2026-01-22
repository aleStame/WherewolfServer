package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
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

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testBecchino() { assertThat(ruolo.isBecchino()).isTrue(); }

    @Test public void testNegromante()
    {
        ruolo.riconosciNegromante();
        verificaFazione(NEGROMANTE);
    }

    private void testStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFazione(Fazione risultato) { assertThat(ruolo.getFazione()).isEqualTo(risultato); }

}