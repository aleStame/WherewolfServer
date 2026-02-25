package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.INQUISIZIONE;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    private Boia ruolo;

    @BeforeEach public void setUp() { ruolo = new Boia(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Boia"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(INQUISIZIONE); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
            "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testBoia() { assertThat(ruolo.isBoia()).isTrue(); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}