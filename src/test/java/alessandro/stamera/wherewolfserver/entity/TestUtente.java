package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public final class TestUtente
{
    private static final String ESEMPIO_USERNAME = "alessandro", ESEMPIO_PASSWORD = "passwordsupersegreta";

    private Utente esempio;

    @BeforeEach public void setUp() { esempio = new Utente(ESEMPIO_USERNAME, ESEMPIO_PASSWORD); }

    @Test public void loginRiuscito() { assertThat(login(ESEMPIO_USERNAME, ESEMPIO_PASSWORD)).isTrue(); }

    @ParameterizedTest @CsvSource( { "alessandro, passworderrata", "marco, passwordsupersegreta", "pino, passwordsbagliata" })
    public void loginNonRiuscito(String username, String password) { assertThat(login(username, password)).isFalse(); }

    @Test public void username() { assertThat(esempio.getUsername()).isEqualTo(ESEMPIO_USERNAME); }

    @Test public void cambioPasswordRiuscito() { assertThatNoException().isThrownBy(() -> esempio.cambiaPassword("nuovapassword")); }

    private boolean login(String username, String password) { return esempio.login(username, password); }

}