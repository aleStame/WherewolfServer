package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPotere
{

    @Test public void testUtilizzoPotere()
    {
        Potere potere = new Potere();
        assertThat(potere.isPotereUtilizzato()).isFalse();
        potere.utilizzaPotere();
        assertThat(potere.isPotereUtilizzato()).isTrue();
    }

}