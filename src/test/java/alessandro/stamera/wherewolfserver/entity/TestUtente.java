package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class TestUtente
{
    private static final String ESEMPIO_USERNAME = "alessandro", ESEMPIO_PASSWORD = "passwordsupersegreta";

    private Utente esempio;

    @BeforeEach public void setUp() { esempio = new Utente(ESEMPIO_USERNAME, ESEMPIO_PASSWORD); }

    @Test public void loginRiuscito() { controllaCondizioneVera(login(ESEMPIO_USERNAME, ESEMPIO_PASSWORD)); }

    @ParameterizedTest @CsvSource( { "alessandro, passworderrata", "marco, passwordsupersegreta", "pino, passwordsbagliata" })
    public void loginNonRiuscito(String username, String password) { controllaCondizioneFalsa(login(username, password)); }

    @Test public void username() { assertThat(esempio.getUsername()).isEqualTo(ESEMPIO_USERNAME); }

    @Test public void cambioPasswordRiuscito() { assertThatNoException().isThrownBy(() -> cambiaPassword("nuovapassword")); }

    @Test public void cambioPasswordNonRiuscito()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> cambiaPassword(ESEMPIO_PASSWORD))
            .withMessage("ERRORE!!! Password uguale alla precedente");
    }

    @Test public void controlloPasswordGiusta() { controllaCondizioneVera(controlloPassword("passwordsupersegreta")); }

    @Test public void controlloPasswordErrata() { controllaCondizioneFalsa(controlloPassword("passwordcasuale")); }

    private void controllaCondizioneVera(boolean condizione) { assertThat(condizione).isTrue(); }

    private void controllaCondizioneFalsa(boolean condizione) { assertThat(condizione).isFalse(); }

    private boolean login(String username, String password) { return esempio.login(username, password); }

    private void cambiaPassword(String password) { esempio.cambiaPassword(password); }

    private boolean controlloPassword(String password) { return esempio.controlloPassword(password); }

}