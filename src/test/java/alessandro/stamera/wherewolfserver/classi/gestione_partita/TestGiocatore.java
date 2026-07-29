package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.RuoloNullo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        cambiaRuolo("Oratore");
        verificaVero(giocatore.isOratore());
    }

    @Test public void testAngeloCustode()
    {
        cambiaRuolo("Angelo custode");
        verificaVero(giocatore.isAngeloCustode());
    }

    @ParameterizedTest @CsvSource({ "Azzeccagarbugli", "Bocca di rosa", "Borgomastro", "Mercante", "Oratore" })
    public void testCitta(String nomeRuolo)
    {
        cambiaRuolo(nomeRuolo);
        verificaVero(giocatore.isCitta());
    }

    @Test public void testContadinoMostro()
    {
        cambiaRuolo("Contadino mostro");
        verificaVero(giocatore.isContadinoMostro());
    }

    @Test public void testNosferatu()
    {
        cambiaRuolo("Contadino mostro");
        verificaVero(giocatore.isNosferatu());
    }

    private void verificaNumeroVoti(int numeroVoti) { assertThat(giocatore.getNumeroVoti()).isEqualTo(numeroVoti); }

    private void verificaNonAmato() { assertThat(isAmato()).isFalse(); }

    private void cambiaRuolo(String nomeRuolo) { giocatore.cambiaRuolo(FACTORY.getRuolo(nomeRuolo)); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAmato() { return giocatore.isAmato(); }

}