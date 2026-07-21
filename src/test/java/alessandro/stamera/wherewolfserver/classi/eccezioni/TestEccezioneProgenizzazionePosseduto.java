package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazionePosseduto
{

    @ParameterizedTest @CsvSource
    (
        {
            "Vampiro, 'Il Vampiro (Elena) non può vampirizzare il Posseduto (Achille).\nElena diventerà il Posseduto e Achille che morirà'",
            "Nosferatu, 'Il Nosferatu (Elena) non può progenizzare il Posseduto (Achille).\nElena diventerà il Posseduto e Achille che morirà'"
        }
    )
    public void testMessaggioErrore(String ruoloProgenizzatore, String messaggio)
    {
        EccezioneProgenizzazionePosseduto eccezione = new EccezioneProgenizzazionePosseduto(ruoloProgenizzatore, "Elena", "Achille");
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}