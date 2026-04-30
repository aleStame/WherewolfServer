package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPotere
{

    @Test public void testUtilizzoPotere()
    {
        Potere potere = new Potere();
        verificaFalso(potere.isPotereUtilizzato());
        potere.utilizzaPotere();
        assertThat(potere.isPotereUtilizzato()).isTrue();
        potere.riabilitaPotere();
        verificaFalso(potere.isPotereUtilizzato());
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}