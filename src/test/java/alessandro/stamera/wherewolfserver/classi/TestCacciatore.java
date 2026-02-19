package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatore
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Cacciatore(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Cacciatore"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Se la Nonna si trasforma in lupo, il Cacciatore è protetto dal lupo ex Nonna. Se in gioco è rimasto l'ultimo lupo del branco " +
            "(quindi senza contare l'eventuale presenza del Lupo solitario) o solo il LUPO SOLITARIO, il Cacciatore è protetto da questo lupo e " +
            "se viene da questo attaccato, il lupo muore, anche se uno tra il Lupo ed il Cacciatore fosse Romeo, l'Amato o protetto dalla " +
            "Strega.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { assertThat(ruolo.isCacciatore()).isTrue(); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}