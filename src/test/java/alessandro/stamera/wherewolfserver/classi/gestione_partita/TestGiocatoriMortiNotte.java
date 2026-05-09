package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriMortiNotte
{

    private GiocatoriMortiNotte giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriMortiNotte(); }

    @Test public void testProgenieNosferatu()
    {
        String nome = "Marco";
        giocatori.aggiungiGiocatore(nome, FACTORY.getRuolo("Prete"));
        assertThat(giocatori.progenizzazioneNosferatu(nome)).isEqualTo(RIUSCITO);
    }
    @Test public void testLupo()
    {
        String[][] giocatori = new String[][] { { "Katia", "Nosferatu" }, { "Valeria", "Giovane lupo" } };
        for(String[] giocatore : giocatori) this.giocatori.aggiungiGiocatore(giocatore[0], FACTORY.getRuolo(giocatore[1]));
        assertThat(this.giocatori.isLupo(giocatori[0][0])).isFalse();
        assertThat(this.giocatori.isLupo(giocatori[1][0])).isTrue();
    }


}