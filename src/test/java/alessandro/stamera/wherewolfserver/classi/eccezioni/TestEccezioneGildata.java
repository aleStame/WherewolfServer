package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneGildata
{

    private EccezioneGildata eccezione;

    @Test public void testGildataFallita()
    {
        eccezione = new EccezioneGildata("Salvo");
        verificaMessaggioErrore("Impossibile criminalizzare Salvo.");
    }

    @Test public void testMortePostGildata()
    {
        eccezione = new EccezioneGildata("Salvo", "Agato");
        verificaMessaggioErrore("Impossibile criminalizzare Salvo.\nIl Capo gilda (Agato) muore.");
    }

    private void verificaMessaggioErrore(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}