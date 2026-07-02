package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriMortiNotte
{

    private GiocatoriMortiNotte giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriMortiNotte(); }

    @ParameterizedTest @CsvSource({ "Prete, RIUSCITO", "Eremita, FALLITO" })
    public void testProgenieNosferatu(String nomeRuolo, EsitoAttacco esito)
    {
        String nome = "Marco";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.progenizzazioneNosferatu(nome)).isEqualTo(esito);
        giocatori.ripristina(nome);
    }

    @Test public void testLupo()
    {
        String[][] giocatori = new String[][] { { "Katia", "Nosferatu" }, { "Valeria", "Giovane lupo" } };
        aggiungiGiocatori(giocatori);
        verificaFalso(isLupo(giocatori[0][0]));
        verificaVero(isLupo(giocatori[1][0]));
    }

    @Test public void testPazzo()
    {
        String[][] giocatori = new String[][] { { "Pablo", "Templare" }, { "Pedro", "Pazzo" } };
        aggiungiGiocatori(giocatori);
        verificaFalso(isPazzo(giocatori[0][0]));
        verificaVero(isPazzo(giocatori[1][0]));
    }

    /*@ParameterizedTest @Enum public void testContadinoPresente()
    {

    }*/

    private void aggiungiGiocatori(String[][] giocatori)
    {
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isPazzo(String nome) { return this.giocatori.isPazzo(nome); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

    private boolean isLupo(String nome) { return giocatori.isLupo(nome); }

}