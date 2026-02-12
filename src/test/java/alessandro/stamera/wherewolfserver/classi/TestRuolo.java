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
        verificaFalso(isAmato());
        verificaFalso(isAccusato());
    }

    @Test public void testAccusato()
    {
        ruolo.accusa();
        verificaAccusato();
    }

    @Test public void testVoti()
    {
        incrementaVoti(1);
        verificaVoti(1);
        ruolo.annullaVoti();
        verificaNessunVoto();
    }

    @Test public void testSegnalazioneAzzeccagarbugli()
    {
        incrementaVoti(1);
        ruolo.segnalazioneAzzeccagarbugli();
        verificaVoti(1);
        verificaAccusato();
    }

    @Test public void testSceltaAngeloCustode()
    {
        ruolo.sceltaAngeloCustode();
        assertThat(isAmato()).isTrue();
    }

    private void verificaAccusato() { assertThat(isAccusato()).isTrue(); }

    private void incrementaVoti(int voti) { ruolo.incrementaVoti(voti); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

    private boolean isAmato() { return ruolo.isAmato(); }

    private void verificaNessunVoto() { assertThat(getNumeroVoti()).isZero(); }

    private void verificaVoti(int voti) { assertThat(getNumeroVoti()).isEqualTo(voti); }

    private int getNumeroVoti() { return ruolo.getNumeroVoti(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}