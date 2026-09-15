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
        assertThat(new EccezioneRuoloProtetto("Ladra", "Giancarlo").getMessage()).isEqualTo(messaggio);
    }

    @Test public void testEccezioneEremita()
    {
        String messaggio = "Giancarlo è l'Eremita.\nAvvisa i lupi che l'attacco fallisce";
        assertThat(new EccezioneRuoloProtetto("Eremita", "Giancarlo").getMessage()).isEqualTo(messaggio);
    }

}