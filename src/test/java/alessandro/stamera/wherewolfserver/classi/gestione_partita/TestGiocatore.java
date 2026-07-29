package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatore
{

    private Giocatore giocatore;

   @BeforeEach public void setUp() { giocatore = new Giocatore(); }

    private static final int ESEMPIO_VOTI = 3;

    @Test public void testVoti()
    {
        giocatore.incrementaVoti(ESEMPIO_VOTI);
        assertThat(giocatore.getNumeroVoti()).isEqualTo(ESEMPIO_VOTI);
        giocatore.annullaVoti();
        assertThat(giocatore.getNumeroVoti()).isEqualTo(0);
        giocatore.maledizione();
        assertThat(giocatore.getNumeroVoti()).isEqualTo(1);
        assertThat(giocatore.isMaledetto()).isTrue();
        assertThat(giocatore.getAura()).isEqualTo(NERA);
    }

    @Test public void testAmato()
    {
        assertThat(giocatore.isAmato()).isFalse();
        giocatore.protezioneAngeloCustode();
        assertThat(giocatore.isAmato()).isTrue();
        giocatore.annullaProtezioneAngeloCustode();
        assertThat(giocatore.isAmato()).isFalse();
    }

}