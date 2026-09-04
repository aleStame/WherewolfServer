package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneVampirizzazioneFallita
{

    @Test public void testAvvisoErroreRomeo()
    {
        String messaggio = "Impossibile vampirizzare Mario perché Romeo.\nAvvisa il Vampiro (Lorenzo) della mancata vampirizzazione.";
        assertThat(new EccezioneVampirizzazioneFallita("Mario", "Lorenzo", "Giulietta").getMessage()).isEqualTo(messaggio);
    }

    @Test public void testAvvisoErroreStrega()
    {
        String messaggio =
            "Impossibile vampirizzare Mario perché protetto dalla Strega.\nAvvisa il Vampiro (Lorenzo) della mancata vampirizzazione.";
        assertThat(new EccezioneVampirizzazioneFallita("Mario", "Lorenzo", "Strega").getMessage()).isEqualTo(messaggio);
    }

}