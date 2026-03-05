package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestInquisitore
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Inquisitore(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Inquisitore"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    @Test public void testBoia() { assertThat(ruolo.isBoia()).isFalse(); }

    @Test public void testInquisitore() { verificaVero(ruolo.isInquisitore()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}