package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiulietta
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Giulietta(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Giulietta"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte indica un giocatore, Romeo, che la riconosce. Quel giocatore diventa protetto dalle creature dell'ombra finché " +
            "Giulietta è in gioco, e la sua fazione diventa Amanti. Se uno dei due viene ucciso di notte o messo al rogo, l'altro si uccide " +
            "durante la notte.";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testGiulietta() { assertThat(ruolo.isGiulietta()).isTrue(); }

    @Test public void testAngeloCustode() { assertThat(ruolo.isAngeloCustode()).isFalse(); }

}