package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoccaDiRosa
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new BoccaDiRosa(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Bocca di rosa"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CITTA); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testLune() { verificaIntero(ruolo.getLune(), 2); }

    @Test public void testBoccaDiRosa() { assertThat(ruolo.isBoccaDiRosa()).isTrue(); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusata. In ogni votazione i voti che Bocca di rosa riceve vengono dimezzati, " +
            "arrotondando per eccesso.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isFalse(); }

    @Test public void testVoti()
    {
        for(int i = 0; i < 7; i++) ruolo.incrementaVoti();
        verificaIntero(ruolo.getNumeroVoti(), 3);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        for(int i = 0; i < 7; i++) ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(ruolo.getNumeroVoti()).isZero();
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

}