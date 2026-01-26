package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiovaneLupo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new GiovaneLupo(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Giovane lupo"); }

    @Test public void testCapoBranco() { verificaFalso(ruolo.isCapoBranco()); }

    @Test public void testLupoBranco() { verificaFalso(ruolo.isLupoBranco()); }

    @Test public void testGiovaneLupo() { assertThat(ruolo.isGiovaneLupo()).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}