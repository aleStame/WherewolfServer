package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestNegromante
{

    private static final String NOME = "Negromante";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NEGROMANTE); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua la Megera, riconosce il Becchino e indica due giocatori che diventano maledetti fino a che il Negromante è" +
            " in gioco. Se all'inizio del giorno sono stati maledetti due o più giocatori maledetti, il Moderatore lo comunica pubblicamente. Il " +
            "mattino successivo, se il Negromante è ancora in gioco, tutti i giocatori di fazione diversa da Negromante vengono eliminati.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isTrue(); }

    @Test public void testBecchino() { assertThat(ruolo.isBecchino()).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}