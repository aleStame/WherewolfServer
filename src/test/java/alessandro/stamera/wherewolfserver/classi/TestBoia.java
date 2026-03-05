package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    private Boia ruolo;

    @BeforeEach public void setUp() { ruolo = new Boia(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Boia"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
            "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testBoia() { verificaVero(ruolo.isBoia()); }

    @Test public void testInquisitore() { assertThat(ruolo.isInquisitore()).isFalse(); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}