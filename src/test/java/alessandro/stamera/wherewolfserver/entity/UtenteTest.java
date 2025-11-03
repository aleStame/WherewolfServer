package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class UtenteTest
{

    @Test public void testGettersAndSetters()
    {
        Utente u = new Utente();
        u.setUsername("alice");
        u.setPassword("secret");
        assertThat(u.getUsername()).isEqualTo("alice");
        assertThat(u.getPassword()).isEqualTo("secret");
        Utente u2 = new Utente("bob", "1234");
        assertThat(u2.getUsername()).isEqualTo("bob");
        assertThat(u2.getPassword()).isEqualTo("1234");
    }
}