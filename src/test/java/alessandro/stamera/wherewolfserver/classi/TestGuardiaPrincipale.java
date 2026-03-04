package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaPrincipale
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new GuardiaPrincipale(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Guardia"); }

    @Test public void testGuardia() { verificaVero(ruolo.isGuardia()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}