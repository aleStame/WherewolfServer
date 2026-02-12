package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
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

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testVoti()
    {
        esempioVoti();
        verificaIntero(getNumeroVoti(), 4);
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        esempioVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(getNumeroVoti()).isZero();
        verificaFalso(ruolo.isAccusato());
    }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

    private void esempioVoti() { ruolo.incrementaVoti(7); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

}