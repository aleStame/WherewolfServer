package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class TestContadinoNormale
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new ContadinoNormale(); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaVero(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { assertThat(ruolo.isContadinoMostro()).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}