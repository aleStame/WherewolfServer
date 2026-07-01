package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoGiocatoreProtetto
{

    private EccezioneAttaccoGiocatoreProtetto eccezione;

    @Test public void testErroreAttaccoProtetto()
    {
        generaEccezione(false, "Gianluigi");
        String messaggio = "Gianluigi non muore perché protetto dalla Strega.\nAvvisa i lupi della sua mancata morte.";
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

    @Test public void testErroreAttaccoRomeo()
    {
        generaEccezione(true, "Antongiulio");
        String messaggio = "Antongiulio non muore perché Romeo.\nAvvisa i lupi della sua mancata morte.";
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

    private void generaEccezione(boolean isRomeo, String nomeVittima) { eccezione = new EccezioneAttaccoGiocatoreProtetto(isRomeo, nomeVittima); }

}