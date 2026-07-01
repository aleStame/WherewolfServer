package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneProgenizzazioneNonRiuscita
{

    private EccezioneProgenizzazioneNonRiuscita eccezione;

    @Test public void testAvvisoMorteAngeloCustode()
    {
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Aldo) causa la morte dell'Angelo custode (Giovanni) del Vampiro amato (" +
            "Giacomo).\nAvvisa Giovanni della sua morte.";
        eccezione = new EccezioneProgenizzazioneNonRiuscita("Aldo", "Giovanni", "Giacomo");
        verificaMessaggio(messaggio);
    }

    @Test public void testAvvisoMorteGhoul()
    {
        String messaggio =
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (Katia) causa la morte del Ghoul (Valeria).\nAvvisa Valeria della sua morte";
        eccezione = new EccezioneProgenizzazioneNonRiuscita("Katia", "Valeria");
        verificaMessaggio(messaggio);
    }

    private void verificaMessaggio(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}