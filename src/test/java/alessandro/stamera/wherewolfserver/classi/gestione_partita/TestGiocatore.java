package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatore
{

    private static final int ESEMPIO_VOTI = 3;

    @Test public void testVoti()
    {
        Giocatore giocatore = new Giocatore();
        giocatore.incrementaVoti(ESEMPIO_VOTI);
        assertThat(giocatore.getNumeroVoti()).isEqualTo(ESEMPIO_VOTI);
        giocatore.annullaVoti();
        assertThat(giocatore.getNumeroVoti()).isEqualTo(0);
        giocatore.maledizione();
        assertThat(giocatore.getNumeroVoti()).isEqualTo(1);
        assertThat(giocatore.isMaledetto()).isTrue();
        assertThat(giocatore.getAura()).isEqualTo(NERA);
    }

}