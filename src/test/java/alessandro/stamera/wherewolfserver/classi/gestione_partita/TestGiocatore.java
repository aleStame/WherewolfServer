package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatore
{

    private Giocatore giocatore;

   @BeforeEach public void setUp() { giocatore = new Giocatore(RuoloNullo.getInstance()); }

    private static final int ESEMPIO_VOTI = 3;

    @Test public void testVoti()
    {
        giocatore.incrementaVoti(ESEMPIO_VOTI);
        verificaNumeroVoti(ESEMPIO_VOTI);
        giocatore.annullaVoti();
        verificaNumeroVoti(0);
        giocatore.maledizione();
        verificaNumeroVoti(1);
        verificaVero(giocatore.isMaledetto());
        assertThat(giocatore.getAura()).isEqualTo(NERA);
    }

    @Test public void testAmato()
    {
        verificaNonAmato();
        giocatore.protezioneAngeloCustode();
        verificaVero(isAmato());
        giocatore.annullaProtezioneAngeloCustode();
        verificaNonAmato();
    }

    @Test public void testOratore()
    {
        giocatore.cambiaRuolo(FACTORY.getRuolo("Oratore"));
        verificaVero(giocatore.isOratore());
    }

    private void verificaNumeroVoti(int numeroVoti) { assertThat(giocatore.getNumeroVoti()).isEqualTo(numeroVoti); }

    private void verificaNonAmato() { assertThat(isAmato()).isFalse(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAmato() { return giocatore.isAmato(); }

}