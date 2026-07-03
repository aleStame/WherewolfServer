package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneNonnaBeccata
{

    @Test public void testMessaggioErrore()
    {
        String messaggio =
            "Il Capo branco (Ciro) ha beccato la Nonna (Federica).\nSveglia Federica e avvisa i due giocatori che Ciro è eliminato e che Federica " +
            "è il Capo branco.";
        assertThat(new EccezioneNonnaBeccata("Ciro", "Capo branco", "Federica").getMessage()).isEqualTo(messaggio);
    }

}