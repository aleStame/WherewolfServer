package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneCacciatore
{

    @Test public void testMessaggioCacciatore()
    {
        String messaggio = "Milo è il Cacciatore ed è protetto dall'attacco del lupo ex Nonna.\nAvvisa i lupi dell'attacco fallito.";
        assertThat(new EccezioneCacciatore("Milo").getMessage()).isEqualTo(messaggio);
    }

    @Test public void testMessaggioCacciatoreLupoNonna()
    {
        String messaggio = "Milo è l'ultimo lupo rimasto in gioco.\nAvvisalo dell'attacco fallito al Cacciatore (Lucio).";
        assertThat(new EccezioneCacciatore("Milo", "Lucio").getMessage()).isEqualTo(messaggio);
    }

}