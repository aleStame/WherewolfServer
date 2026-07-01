package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoGiocatoreProtetto
{

    private EccezioneAttaccoGiocatoreProtetto eccezione;

    @Test public void testErroreAttaccoProtetto()
    {
        generaEccezione(false, "Gianluigi");
        verificaMessaggio("Gianluigi non muore perché protetto dalla Strega.\nAvvisa i lupi della sua mancata morte.");
    }

    @Test public void testErroreAttaccoRomeo()
    {
        generaEccezione(true, "Antongiulio");
        verificaMessaggio("Antongiulio non muore perché Romeo.\nAvvisa i lupi della sua mancata morte.");
    }

    private void generaEccezione(boolean isRomeo, String nomeVittima)
    {
        eccezione = new EccezioneAttaccoGiocatoreProtetto(isRomeo, nomeVittima);
    }

    private void verificaMessaggio(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}