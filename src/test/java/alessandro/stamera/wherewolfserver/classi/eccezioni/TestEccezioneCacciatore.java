package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneCacciatore
{

    private EccezioneCacciatore eccezione;

    @Test public void testMessaggioCacciatore()
    {
        eccezione = new EccezioneCacciatore("Milo");
        verificaMessaggioErrore("Milo è il Cacciatore ed è protetto dall'attacco del lupo ex Nonna.\nAvvisa i lupi dell'attacco fallito.");
    }

    @Test public void testMessaggioCacciatoreLupoNonna()
    {
        eccezione = new EccezioneCacciatore("Milo", "Lucio");
        verificaMessaggioErrore("Milo è l'ultimo lupo rimasto in gioco.\nAvvisalo dell'attacco fallito al Cacciatore (Lucio).");
    }

    private void verificaMessaggioErrore(String messaggio) { assertThat(eccezione.getMessage()).isEqualTo(messaggio); }

}