package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.*;

public final class TestBallottaggio
{

    private static final String ERRORE_ROGO_SALTATO =
        "Il villaggio non ha trovato accordo su chi mandare al rogo: non viene bruciato nessuno!";

    private Ballottaggio ballottaggio;

    @BeforeEach public void setUp() { ballottaggio = new Ballottaggio(); }

    @Test public void testAmatoPresente()
    {
        String nome = "Gabriella";
        ballottaggio.aggiungiGiocatore(nome, getMercanteAmato());
        verificaVero(isAmatoPresente());
        assertThat(ballottaggio.getNomeAmato()).isEqualTo(nome);
    }

    @Test public void testAmatoAssente()
    {
        aggiungiGiocatore("Lucia", "Mercante");
        verificaFalso(isAmatoPresente());
    }

    @Test public void testNessunaSegnalazione() {
        verificaSegnalazioneAssente();
    }

    @ParameterizedTest @CsvSource({ "Guaritore, 0", "Lupo del branco, 0", "Peccatore, 2" })
    public void testSegnalazioneBoia(String nomeRuolo, int risultato)
    {
        String nome = "Miriam";
        String[][] giocatori = new String[][] { { nome, nomeRuolo }, { "Andrea", "Pazzo" }, { "Sara", "Giullare" } };
        aggiungiGiocatori(giocatori);
        verificaBoiata(nome, 2, risultato, toArray(stream(estraiNomiGiocatori(giocatori)).filter(stringa -> !stringa.equals(nome))));
    }

    @ParameterizedTest @CsvSource({ "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" })
    public void testSegnalazioneBoiaContadinoLupoAttaccato(String tipoLupo)
    {
        String nome = "Tony";
        String[][] giocatori = new String[][] { { "Andrea", "Pazzo" }, { "Sara", "Giullare" } };
        Ruolo ruolo = FACTORY.getRuolo("Contadino discendente dei lupi");
        assertThat(ruolo.attaccoLupi(FACTORY.getRuolo(tipoLupo))).isEqualTo(FALLITO);
        ballottaggio.aggiungiGiocatore(nome, ruolo);
        aggiungiGiocatori(giocatori);
        verificaBoiata(nome, 3, 0, estraiNomiGiocatori(giocatori));
    }

    @Test public void testSegnalazioneBoiaContadinoLupoNonAttaccato()
    {
        String nome = "Tony";
        String[][] giocatori = new String[][] { { "Andrea", "Pazzo" }, { "Sara", "Giullare" } };
        aggiungiGiocatori(new String[][]{ { nome, "Contadino discendente dei lupi" } });
        aggiungiGiocatori(giocatori);
        verificaBoiata(nome, 3, 0, estraiNomiGiocatori(giocatori));
    }

    @Test public void testPerdenteBallottaggio()
    {
        String[][] giocatori = new String[][] { { "Davide", "Prete" }, { "Margherita", "Guardia" } };
        aggiungiGiocatori(giocatori);
        int[] numeroVoti = new int[] { 1, 2 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(giocatori[i][0], numeroVoti[i]);
        verificaNomeEliminato(giocatori[1][0]);
    }

    @Test public void testPareggioBallottaggio()
    {
        String[][] giocatori = new String[][]{ { "Francesco", "Capo branco" }, { "Luca", "Altra guardia" } };
        aggiungiGiocatori(giocatori);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 1);
        assertThatIllegalArgumentException().isThrownBy(this::getNomeGiocatorePerdente).withMessage(ERRORE_ROGO_SALTATO);
    }

    @Test public void testCitta()
    {
        String[][] giocatori = new String[][] { { "Davide", "Bocca di rosa" }, { "Dina", "Inquisitore" } };
        aggiungiGiocatori(giocatori);
        verificaVero(isCitta(giocatori[0][0]));
        verificaFalso(isCitta(giocatori[1][0]));
    }

    @Test public void testSegnalazioneOratore()
    {
        String[][] giocatori = new String[][] { { "Antonella", "Prete" }, { "Luca", "Peccatore" }, { "Margherita", "Azzeccagarbugli" } };
        aggiungiGiocatori(giocatori);
        for(int i = 0; i < giocatori.length - 1; i++) segnalazioneOratore(giocatori[i][0]);
        incrementaVoti(giocatori[1][0], 3);
        assertThatIllegalStateException().isThrownBy(this::getNomeGiocatorePerdente).withMessage(ERRORE_ROGO_SALTATO);
        verificaSegnalazioneAssente();
    }

    @Test public void testSegnalazioneOratoreNonRiuscita()
    {
        String[][] giocatori = new String[][] { { "Aldo", "Capo branco" }, { "Giovanni", "Lupo del branco" }, { "Giacomo", "Giovane lupo" } };
        aggiungiGiocatori(giocatori);
        segnalazioneOratore(giocatori[0][0]);
        int posizione = 2;
        incrementaVoti(giocatori[posizione][0], 3);
        verificaNomeEliminato(giocatori[posizione][0]);
        verificaSegnalazioneAssente();
    }

    @Test public void testSegnalazioneBorgomastro()
    {
        verificaFalso(isSegnalazioneBorgomastroAvvenuta());
        ballottaggio.segnalazioneBorgomastro();
        verificaVero(isSegnalazioneBorgomastroAvvenuta());
    }

    private void verificaBoiata(String nome, int numeroVoti, int risultato, String... giocatori)
    {
        incrementaVoti(nome, numeroVoti);
        for(String giocatore : giocatori) incrementaVoti(giocatore, numeroVoti);
        ballottaggio.segnalazioneBoia(nome);
        verificaNumeroVoti(nome, numeroVoti);
        for(String giocatore : giocatori) verificaNumeroVoti(giocatore, risultato);
        verificaNumeroVoti(nome, numeroVoti);
        FACTORY.annullaSegnalazioni();
    }

    private String[] estraiNomiGiocatori(String[][] giocatori)
    {
        return toArray(stream(giocatori).map(giocatore -> giocatore[0]));
    }

    private String[] toArray(Stream<String> stream) { return stream.toList().toArray(new String[0]); }

    private boolean isSegnalazioneBorgomastroAvvenuta() { return ballottaggio.isSegnalazioneBorgomastroAvvenuta(); }

    private void verificaSegnalazioneAssente() { verificaVero(ballottaggio.isSegnalazioneAssente()); }

    private void segnalazioneOratore(String nome) { ballottaggio.segnalazioneOratore(nome); }

    private void verificaNomeEliminato(String nome) { assertThat(getNomeGiocatorePerdente()).isEqualTo(nome); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isCitta(String nome) { return ballottaggio.isCitta(nome); }

    private void incrementaVoti(String nome, int numeroVoti) { ballottaggio.incrementaVoti(nome, numeroVoti); }

    private String getNomeGiocatorePerdente() { return ballottaggio.getNomeGiocatorePerdente(); }

    private void aggiungiGiocatori(String[][] giocatori)
    {
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
    }

    private void verificaNumeroVoti(String nome, int numeroVoti)
    {
        assertThat(ballottaggio.getNumeroVoti(nome)).isEqualTo(numeroVoti);
    }

    private boolean isAmatoPresente() { return ballottaggio.isAmatoPresente(); }

    private void aggiungiGiocatore(String nome, String nomeRuolo)
    {
        ballottaggio.aggiungiGiocatore(nome, FACTORY.getRuolo(nomeRuolo));
    }

    private Ruolo getMercanteAmato()
    {
        Ruolo ruolo = getMercante();
        ruolo.sceltaAngeloCustode();
        return ruolo;
    }

    private Ruolo getMercante() { return FACTORY.getRuolo("Mercante"); }
    
    

}