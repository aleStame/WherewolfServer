package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoGiocatoreProtetto
{

    @Test public void testErroreAttaccoProtetto()
    {
        EccezioneAttaccoGiocatoreProtetto eccezione = new EccezioneAttaccoGiocatoreProtetto(false, "Gianluigi");
        String messaggio = "Gianluigi non muore perché protetto dalla Strega.\nAvvisa i lupi della sua mancata morte.";
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

    @Test public void testErroreAttaccoRomeo()
    {
        EccezioneAttaccoGiocatoreProtetto eccezione = new EccezioneAttaccoGiocatoreProtetto(true, "Antongiulio");
        String messaggio = "Antongiulio non muore perché Romeo.\nAvvisa i lupi della sua mancata morte.";
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}