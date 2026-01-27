package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLupoReietto
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new LupoReietto(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Lupo reietto"); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { verificaFalso(ruolo.isGiovaneLupo()); }

    @Test public void testLupoReietto() { assertThat(ruolo.isLupoReietto()).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}