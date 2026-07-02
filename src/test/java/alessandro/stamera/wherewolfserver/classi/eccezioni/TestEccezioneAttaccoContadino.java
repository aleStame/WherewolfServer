package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.EROE;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino.MOSTRO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoContadino
{

    @Test public void testContadinoEroe()
    {
        EccezioneAttaccoContadino eccezione = new EccezioneAttaccoContadino(EROE, "Nadia", "Elena");
        String messaggio =
            "L'attacco al Contadino eroe (Nadia) causa la morte anche del lupo attaccante (Elena).\nAvvisa entrambi i giocatori della loro morte.";
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

    @Test public void testContadinoMostro()
    {
        EccezioneAttaccoContadino eccezione = new EccezioneAttaccoContadino(MOSTRO, "Nadia", "Elena");
        String messaggio =
            "L'attacco al Contadino mostro (Nadia) causa la morte anche del lupo attaccante (Elena).\nAvvisa entrambi i giocatori della loro morte.";
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}