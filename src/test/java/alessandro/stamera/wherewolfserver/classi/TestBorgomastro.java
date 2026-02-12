package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBorgomastro
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Borgomastro(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Borgomastro"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testBorgomastro() { verificaVero(ruolo.isBorgomastro()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}