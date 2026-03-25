package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMegera
{

    @Test public void testNome() { assertThat(FACTORY.getRuolo("Megera").getNome()).isEqualTo("Megera"); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuata da tutte le creature dell'ombra. Se viene indicata da un mistico, fino a che la Megera è in gioco, " +
            "quel giocatore diventa Maledetto, riceverà sempre responsi negativi e non potrà più proteggere. Vince con qualsiasi creatura " +
            "dell'ombra";
        assertThat(FACTORY.getRuolo("Megera").getDescrizione()).isEqualTo(descrizione);
    }

}