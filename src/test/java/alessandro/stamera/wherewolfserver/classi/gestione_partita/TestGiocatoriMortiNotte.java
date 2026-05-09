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
    @Test public void testLupo()
    {
        String[][] giocatori = new String[][] { { "Katia", "Nosferatu" }, { "Valeria", "Giovane lupo" } };
        GiocatoriMortiNotte morti = new GiocatoriMortiNotte();
        for(String[] giocatore : giocatori) morti.aggiungiGiocatore(giocatore[0], FACTORY.getRuolo(giocatore[1]));
        assertThat(morti.isLupo(giocatori[0][0])).isFalse();
        assertThat(morti.isLupo(giocatori[1][0])).isTrue();
    }


}