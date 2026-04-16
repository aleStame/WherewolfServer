package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatori
{

    @Test public void testInserimentoGiocatori()
    {
        Giocatori giocatori = new Giocatori();
        giocatori.aggiungiGiocatore("Antonio", FACTORY.getRuolo("Capo branco"));
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(1);
    }

    @Test public void testEliminazioneGiocatori()
    {
        Giocatori giocatori = new Giocatori();
        String eliminato = "Elena";
        String[][] abbinamenti = new String[][] { { "Tommaso", "Veggente" }, { eliminato, "Mercante" } };
        for (String[] strings : abbinamenti) giocatori.aggiungiGiocatore(strings[0], FACTORY.getRuolo(strings[1]));
        giocatori.eliminaGiocatore(eliminato);
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(1);
    }

}