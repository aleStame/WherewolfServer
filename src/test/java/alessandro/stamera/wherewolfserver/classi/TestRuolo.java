package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private static final int ESEMPIO_VOTI = 3;

    private Ruolo ruolo;

    @BeforeEach
    public void setUp() { ruolo = new Ruolo(null, null, null, null, -1, false); }

    @Test public void testInizializzazione()
    {
        verificaNessunVoto();
        verificaFalso(isAmato());
        verificaLibero();
    }

    @Test public void testAccusato()
    {
        ruolo.accusa();
        verificaAccusato();
        ruolo.libera();
        verificaLibero();
    }

    @Test public void testVoti()
    {
        incrementaVoti();
        verificaVoti();
        ruolo.annullaVoti();
        verificaNessunVoto();
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        verificaVoti();
        verificaAccusato();
    }

    @Test public void testSceltaAngeloCustode()
    {
        ruolo.sceltaAngeloCustode();
        assertThat(isAmato()).isTrue();
    }

    private void verificaAccusato() { assertThat(isAccusato()).isTrue(); }

    private void verificaLibero() { verificaFalso(isAccusato()); }

    private void incrementaVoti() { ruolo.incrementaVoti(ESEMPIO_VOTI); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private boolean isAmato() { return ruolo.isAmato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private void verificaVoti() { assertThat(getNumeroVoti()).isEqualTo(ESEMPIO_VOTI); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}