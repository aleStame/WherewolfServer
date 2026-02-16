package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCacciatore
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Cacciatore(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Cacciatore"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Se la Nonna si trasforma in lupo, il Cacciatore è protetto dal lupo ex Nonna. Se in gioco è rimasto l'ultimo lupo del branco " +
            "(quindi senza contare l'eventuale presenza del Lupo solitario) o solo il LUPO SOLITARIO, il Cacciatore è protetto da questo lupo e " +
            "se viene da questo attaccato, il lupo muore, anche se uno tra il Lupo ed il Cacciatore fosse Romeo, l'Amato o protetto dalla " +
            "Strega.";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { assertThat(ruolo.isCacciatore()).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}