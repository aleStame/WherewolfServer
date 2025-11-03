package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestUtente
{

    private Utente esempio;

    @BeforeEach public void setUp() { esempio = new Utente("alessandro", "passwordsupersegreta"); }

    @Test public void loginRiuscito() { assertThat(login("alessandro", "passwordsupersegreta")).isTrue(); }

    @ParameterizedTest @CsvSource( { "alessandro, passworderrata", "marco, passwordsupersegreta", "pino, passwordsbagliata" })
    public void loginNonRiuscito(String username, String password) { assertThat(login(username, password)).isFalse(); }

    private boolean login(String username, String password) { return esempio.login(username, password); }

}