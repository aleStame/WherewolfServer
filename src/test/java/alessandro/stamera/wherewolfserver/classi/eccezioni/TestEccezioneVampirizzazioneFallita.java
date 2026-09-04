package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneVampirizzazioneFallita
{

    private EccezioneVampirizzazioneFallita eccezione;

    @Test public void testAvvisoErroreRomeo()
    {
        eccezione = new EccezioneVampirizzazioneFallita("Mario", "Lorenzo", "Giulietta");
        verificaMessaggioErrore("Impossibile vampirizzare Mario perché Romeo.\nAvvisa il Vampiro (Lorenzo) della mancata vampirizzazione.");
    }

    @Test public void testAvvisoErroreStrega()
    {
        eccezione = new EccezioneVampirizzazioneFallita("Mario", "Lorenzo", "Strega");
        String messaggio =
            "Impossibile vampirizzare Mario perché protetto dalla Strega.\nAvvisa il Vampiro (Lorenzo) della mancata vampirizzazione.";
        verificaMessaggioErrore(messaggio);
    }

    @Test public void testAvvisoErrore()
    {
        eccezione = new EccezioneVampirizzazioneFallita("Mario", "Lorenzo");
        String messaggio =
            "Impossibile vampirizzare Mario perché protetto dall'attacco del Vampiro.\nAvvisa il Vampiro (Lorenzo) della mancata vampirizzazione.";
        verificaMessaggioErrore(messaggio);
    }

    private void verificaMessaggioErrore(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}