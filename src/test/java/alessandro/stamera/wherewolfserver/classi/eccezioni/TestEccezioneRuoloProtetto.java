package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneRuoloProtetto
{

    @Test public void testEccezioneLadraProtetta()
    {
        String messaggio =
            "Impossible uccidere la Ladra (Giancarlo) perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi dell'attacco " +
            "fallito.";
        assertThat(new EccezioneRuoloProtetto("Giancarlo").getMessage()).isEqualTo(messaggio);
    }

}