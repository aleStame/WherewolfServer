package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoSolitario
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new LupoSolitario(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Lupo solitario"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(LUPO_SOLITARIO); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { verificaFalso(ruolo.isLupoReietto()); }

    @Test public void testLupoSolitario() { assertThat(ruolo.isLupoSolitario()).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}