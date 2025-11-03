package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestUtente
{

    private Utente esempio;

    @BeforeEach public void setUp() { esempio = new Utente("alessandro", "passwordsupersegreta"); }

    @ParameterizedTest @CsvSource
    (
        {
            "alessandro, passwordsupersegreta, true", "alessandro, passworderrata, false", "marco, passwordsupersegreta, false",
            "pino, passwordsbagliata, false"
        }
    )
    public void login(String username, String password, boolean esito) { assertThat(esempio.login(username, password)).isEqualTo(esito); }
}