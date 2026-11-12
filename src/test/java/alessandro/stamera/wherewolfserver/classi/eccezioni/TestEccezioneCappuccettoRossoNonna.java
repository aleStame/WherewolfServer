package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneCappuccettoRossoNonna
{

    @Test public void testMessaggioErrore()
    {
        String messaggio =
            "Fino a che la Nonna (Pina) è in gioco, Cappuccetto Rosso (Laura) non può essere attaccata dai lupi.\nAvvisali dell'attacco fallito";
        assertThat(new EccezioneCappuccettoRossoNonna("Pina", "Laura").getMessage()).isEqualTo(messaggio);
    }

}