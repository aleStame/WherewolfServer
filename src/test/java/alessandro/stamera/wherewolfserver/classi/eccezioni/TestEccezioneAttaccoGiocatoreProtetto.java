package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoGiocatoreProtetto
{

    @ParameterizedTest @CsvSource
    (
        {
            "false, Gianluigi, 'Gianluigi non muore perché protetto dalla Strega.\nAvvisa i lupi della sua mancata morte.'",
            "true, Antongiulio, 'Antongiulio non muore perché Romeo.\nAvvisa i lupi della sua mancata morte.'"
        }
    )
    public void testMessaggioErrore(boolean isRomeo, String nomeVittima, String messaggio)
    {
        EccezioneAttaccoGiocatoreProtetto eccezione = new EccezioneAttaccoGiocatoreProtetto(isRomeo, nomeVittima);
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}