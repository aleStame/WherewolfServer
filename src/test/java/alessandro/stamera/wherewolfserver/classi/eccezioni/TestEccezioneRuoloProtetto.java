package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneRuoloProtetto
{

    private EccezioneRuoloProtetto eccezione;

    @Test public void testEccezioneLadra()
    {
        eccezione = new EccezioneRuoloProtetto("Ladra", "Giancarlo");
        String messaggio =
            "Impossible uccidere la Ladra (Giancarlo) perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi dell'attacco " +
            "fallito.";
        verificaMessaggioEccezione(messaggio);
    }

    @Test public void testEccezioneLupoReietto()
    {
        String messaggio = "Avvisa il Capo branco (Gianluigi) che non può attaccare il Lupo reietto (Ermenegildo).";
        eccezione = new EccezioneRuoloProtetto("Capo branco", "Gianluigi", "Lupo reietto", "Ermenegildo");
        verificaMessaggioEccezione(messaggio);
    }

    private void verificaMessaggioEccezione(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}