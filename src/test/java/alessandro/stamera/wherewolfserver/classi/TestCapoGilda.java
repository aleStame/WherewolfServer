package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCapoGilda
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CapoGilda(); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Capo gilda"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
            "mistico. Se quel mistico non è in gioco, indica un giocatore. Se è una Guardia o un lupo mannaro, il Capo gilda viene ucciso. Se " +
            "la sua fazione è Città o Villaggio, riconosce il Capo gilda, altrimenti non accade nulla";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

}