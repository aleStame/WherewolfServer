package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoReietto
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new LupoReietto(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Lupo reietto"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Traditore e la Megera e riconosce i lupi del branco. Dalla prima notte, se è il lupo più potente in " +
            "gioco, durante il turno dei lupi, può indicare un giocatore che viene ucciso. É protetto dal Capo branco e perde la partita se " +
            "questi alla fine è ancora in gioco.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLupo() { verificaVero(ruolo.isLupo()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaVero(ruolo.isLupoReietto()); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(LUPO_BRANCO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}