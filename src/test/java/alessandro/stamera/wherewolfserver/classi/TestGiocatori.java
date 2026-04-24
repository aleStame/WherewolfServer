package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatori
{

    private Giocatori giocatori;

    @BeforeEach public void setUp() { giocatori = new Giocatori(); }

    @Test public void testInserimentoGiocatori()
    {
        String[][] giocatori = new String[][] { { "Antonio", "Capo branco" }, { "Stefano", "Prete" } };
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
        verificaNumeroGiocatori(2);
    }

    @Test public void testEliminazioneGiocatori()
    {
        String nome = "Francesca";
        aggiungiGiocatore(nome, "Nonna");
        giocatori.eliminaGiocatore(nome);
        verificaNumeroGiocatori(0);
    }

    @Test public void testVotazione()
    {
        int numeroVoti = 3;
        String nome = "Anna";
        aggiungiGiocatore(nome, "Cappuccetto rosso");
        giocatori.incrementaVoti(nome, numeroVoti);
        verificaNumeroIntero(giocatori.getNumeroVoti(nome), numeroVoti);
    }

    @Test public void testAngeloCustodePresente()
    {
        String nome = "Otello";
        aggiungiGiocatore(nome, "Angelo custode");
        assertThat(giocatori.isAngeloCustodePresente()).isTrue();
        assertThat(giocatori.getNomeAngeloCustode()).isEqualTo(nome);
    }

    @ParameterizedTest @CsvSource({ "Assassino, Capo gilda, Guardia corrotta, Ladra, Spia" })
    public void testCriminale(String nomeCriminale)
    {
        String nome = "Sofia";
        aggiungiGiocatore(nome, nomeCriminale);
        assertThat(giocatori.isCriminale(nome)).isTrue();
    }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

    private void verificaNumeroGiocatori(int numeroGiocatori)
    {
        verificaNumeroIntero(giocatori.getNumeroGiocatori(), numeroGiocatori);
    }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

}