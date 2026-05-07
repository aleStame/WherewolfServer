package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriMortiNotte
{

    @Test public void testProgenieNosferatu()
    {
        String nome = "Marco";
        GiocatoriMortiNotte giocatori = new GiocatoriMortiNotte();
        giocatori.aggiungiGiocatore(nome, FACTORY.getRuolo("Prete"));
        assertThat(giocatori.progenizzazioneNosferatu(nome)).isEqualTo(RIUSCITO);
    }

}