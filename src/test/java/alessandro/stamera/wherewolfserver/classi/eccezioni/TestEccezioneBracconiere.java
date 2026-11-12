package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneBracconiere
{

    @Test public void testMessaggioErrore()
    {
        String messaggio = "Il Bracconiere (Ciccio) ha usato il suo potere, pertanto l'attacco a Giulia fallisce.";
        assertThat(new EccezioneBracconiere("Ciccio", "Giulia").getMessage()).isEqualTo(messaggio);
    }

}