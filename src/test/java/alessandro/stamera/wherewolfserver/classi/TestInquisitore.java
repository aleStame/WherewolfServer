package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisitore
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Inquisitore(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Inquisitore"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Boia e il Templare e scopre quanti mistici sono in gioco. Se viene indicato da un mistico, lo " +
            "riconosce. Può segnalare un giocatore durante le accuse: se è un mistico, sarà accusato a prescindere dai voti ricevuti.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    @Test public void testBoia() { assertThat(ruolo.isBoia()).isFalse(); }

    @Test public void testInquisitore() { verificaVero(ruolo.isInquisitore()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}