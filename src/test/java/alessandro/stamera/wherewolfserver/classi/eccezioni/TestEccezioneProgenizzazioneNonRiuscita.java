package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazioneNonRiuscita
{

    private EccezioneProgenizzazioneNonRiuscita eccezione;

    @Test public void testAvvisoMorteAngeloCustode()
    {
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Aldo) causa la morte dell'Angelo custode (Giovanni) del Vampiro amato (" +
            "Giacomo).\nAvvisa Giovanni della sua morte.";
        eccezione = new EccezioneProgenizzazioneNonRiuscita
        (
"Cacciatore di vampiri", "Aldo", "Giovanni", "Giacomo"
        );
        verificaMessaggio(messaggio);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Cacciatore di vampiri, " +
            "'Il tentativo di vampirizzazione del Cacciatore di vampiri (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua " +
            "morte.'",
            "Contadino mostro, " +
            "'Il tentativo di vampirizzazione del Contadino mostro (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua morte.'"
        }
    )
    public void testAvvisoMorteGhoul(String nomeRuolo, String messaggio)
    {
        eccezione = new EccezioneProgenizzazioneNonRiuscita(nomeRuolo, "Katia", "Valeria");
        verificaMessaggio(messaggio);
    }

    private void verificaMessaggio(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}