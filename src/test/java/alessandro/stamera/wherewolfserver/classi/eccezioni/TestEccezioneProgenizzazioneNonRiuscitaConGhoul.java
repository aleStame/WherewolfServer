package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazioneNonRiuscitaConGhoul
{

    @ParameterizedTest @CsvSource
    (
        {
            "Cacciatore di vampiri, Vampiro, " +
            "'Il tentativo di vampirizzazione del Cacciatore di vampiri (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua " +
            "morte.'",
            "Contadino mostro, Vampiro, " +
            "'Il tentativo di vampirizzazione del Contadino mostro (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua morte.'",
            "Cacciatore di vampiri, Nosferatu, " +
            "'Il tentativo di progenizzazione del Cacciatore di vampiri (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua " +
            "morte.'",
            "Contadino mostro, Nosferatu, " +
            "'Il tentativo di progenizzazione del Contadino mostro (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua morte.'"
        }
    )
    public void testMessaggio(String nomeRuolo, String ruoloProgenizzatore, String messaggio)
    {
        EccezioneProgenizzazioneNonRiuscitaConGhoul eccezione =
            new EccezioneProgenizzazioneNonRiuscitaConGhoul(nomeRuolo, "Katia", ruoloProgenizzatore, "Valeria");
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}