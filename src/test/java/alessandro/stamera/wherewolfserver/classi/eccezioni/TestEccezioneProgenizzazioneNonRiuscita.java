package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazioneNonRiuscita
{

    @Test public void testAvvisoMorteAngeloCustode()
    {
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Aldo) causa la morte dell'Angelo custode (Giovanni) del Vampiro amato (" +
            "Giacomo).\nAvvisa Francesco della sua morte.";
        EccezioneProgenizzazioneNonRiuscita eccezione =
            new EccezioneProgenizzazioneNonRiuscita("Aldo", "Giovanni", "Giacomo");
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}