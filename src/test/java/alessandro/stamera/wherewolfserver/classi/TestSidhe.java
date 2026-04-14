package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestSidhe
{

    private static final String NOME = "Sidhe";

    @Test public void testNome() { assertThat(FACTORY.getRuolo(NOME).getNome()).isEqualTo(NOME); }

    @Test public void testAura() { assertThat(FACTORY.getRuolo(NOME).getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        assertThat(FACTORY.getRuolo(NOME).getDescrizione()).isEqualTo("La prima notte riconosce le altre creature del Piccolo Popolo (Goblin, Leprecauno). È protetta da tutti i Mistici.");
    }

}