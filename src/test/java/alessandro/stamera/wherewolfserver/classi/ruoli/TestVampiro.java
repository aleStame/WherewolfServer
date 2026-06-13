package alessandro.stamera.wherewolfserver.classi.ruoli;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVampiro
{

    private static final String NOME = "Vampiro";

    @Test public void testNome() { assertThat(FACTORY.getRuolo(NOME).getNome()).isEqualTo(NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce il Ghoul. Dalla seconda notte, può indicare un giocatore che viene avvisato. Se è il Cacciatore di vampiri " +
            "o un lupo, il Vampiro viene ucciso. Se è un mistico, non accade nulla. Altrimenti, quel giocatore lo riconosce e diventa una " +
            "progenie vampirica con aura oscura e fazione Vampiro. Inoltre, la prima notte individua la Megera.";
        assertThat(FACTORY.getRuolo(NOME).getDescrizione()).isEqualTo(descrizione);
    }

}