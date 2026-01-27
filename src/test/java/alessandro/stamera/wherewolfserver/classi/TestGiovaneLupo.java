package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiovaneLupo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new GiovaneLupo(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Giovane lupo"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Traditore e riconosce i lupi del branco e nelle altre apre gli occhi durante il turno dei lupi " +
            "mannari. Se viene messo al rogo, la notte successiva i lupi del branco attaccheranno due volte";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { assertThat(ruolo.isGiovaneLupo()).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}