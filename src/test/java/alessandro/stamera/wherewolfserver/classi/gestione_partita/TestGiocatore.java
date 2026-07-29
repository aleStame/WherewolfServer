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
        verificaNumeroVoti(ESEMPIO_VOTI);
        giocatore.annullaVoti();
        verificaNumeroVoti(0);
        giocatore.maledizione();
        verificaNumeroVoti(1);
        assertThat(giocatore.isMaledetto()).isTrue();
        assertThat(giocatore.getAura()).isEqualTo(NERA);
    }

    @Test public void testAmato()
    {
        verificaNonAmato();
        giocatore.protezioneAngeloCustode();
        assertThat(isAmato()).isTrue();
        giocatore.annullaProtezioneAngeloCustode();
        verificaNonAmato();
    }

    private void verificaNumeroVoti(int numeroVoti) { assertThat(giocatore.getNumeroVoti()).isEqualTo(numeroVoti); }

    private void verificaNonAmato() { assertThat(isAmato()).isFalse(); }

    private boolean isAmato() { return giocatore.isAmato(); }

}