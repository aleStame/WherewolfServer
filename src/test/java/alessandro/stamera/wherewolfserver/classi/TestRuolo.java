package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuolo
{

    private Ruolo ruolo;

    @BeforeEach
    public void setUp() { ruolo = new Ruolo(null, null, null, null, -1, false); }

    @Test public void testInizializzazione()
    {
        verificaNessunVoto();
        verificaFalso(ruolo.isAmato());
        verificaFalso(isAccusato());
    }

    @Test public void testAccusato()
    {
        verificaFalso(isAccusato());
        ruolo.accusa();
        verificaAccusato();
    }

    @Test public void testVoti()
    {
        ruolo.incrementaVoti();
        assertThat(getNumeroVoti()).isEqualTo(1);
        ruolo.annullaVoti();
        verificaNessunVoto();
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        ruolo.incrementaVoti();
        ruolo.segnalazioneAzzeccagarbugli();
        assertThat(getNumeroVoti()).isEqualTo(1);
        verificaAccusato();
    }

    @Test public void testSceltaAngeloCustode()
    {
        ruolo.sceltaAngeloCustode();
        assertThat(ruolo.isAmato()).isTrue();
    }

    private void verificaAccusato() { assertThat(isAccusato()).isTrue(); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}