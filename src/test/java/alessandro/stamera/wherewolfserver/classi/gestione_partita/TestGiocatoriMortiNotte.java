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
        aggiungiGiocatore(nome, "Prete");
        assertThat(giocatori.progenizzazioneNosferatu(nome)).isEqualTo(RIUSCITO);
    }
    @Test public void testLupo()
    {
        String[][] giocatori = new String[][] { { "Katia", "Nosferatu" }, { "Valeria", "Giovane lupo" } };
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0],giocatore[1]);
        assertThat(isLupo(giocatori[0][0])).isFalse();
        assertThat(isLupo(giocatori[1][0])).isTrue();
    }

    @Test public void testPazzo()
    {
        String[][] giocatori = new String[][] { { "Pablo", "Templare" }, { "Pedro", "Pazzo" } };
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0],giocatore[1]);
        assertThat(isPazzo(giocatori[0][0])).isFalse();
        assertThat(isPazzo(giocatori[1][0])).isTrue();
    }

    private boolean isPazzo(String nome) { return this.giocatori.isPazzo(nome); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

    private boolean isLupo(String nome) { return giocatori.isLupo(nome); }

}