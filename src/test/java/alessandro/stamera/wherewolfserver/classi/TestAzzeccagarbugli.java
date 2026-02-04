package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAzzeccagarbugli
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Azzeccagarbugli(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Azzeccagarbugli"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città " +
            "o Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CITTA); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testAzzeccagarbugli() { assertThat(ruolo.isAzzeccagarbugli()).isTrue(); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testAngeloCustode() { verificaFalso(ruolo.isAngeloCustode()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}