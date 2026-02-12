package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoccaDiRosa
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new BoccaDiRosa(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Bocca di rosa"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testBoccaDiRosa() { verificaVero(ruolo.isBoccaDiRosa()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusata. In ogni votazione i voti che Bocca di rosa riceve vengono dimezzati, " +
            "arrotondando per eccesso.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testVoti()
    {
        ruolo.incrementaVoti(7);
        assertThat(ruolo.getNumeroVoti()).isEqualTo(4);
    }

    @Test public void testAzzeccagarbugli() { assertThat(ruolo.isAzzeccagarbugli()).isFalse(); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}