package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class TestBallottaggio
{

    private Ballottaggio ballottaggio;

    @BeforeEach public void setUp() { ballottaggio = new Ballottaggio(); }

    @Test public void testAmatoPresente()
    {
        String nome = "Gabriella";
        setMercanteAmato();
        aggiungiGiocatore(nome, "Mercante");
        assertThat(isAmatoPresente()).isTrue();
        assertThat(ballottaggio.getNomeAmato()).isEqualTo(nome);
    }

    @Test public void testAmatoAssente()
    {
        aggiungiGiocatore("Lucia", "Mercante");
        assertThat(isAmatoPresente()).isFalse();
    }

    @Test public void testNessunaSegnalazione() { assertThat(ballottaggio.isSegnalazioneAssente()).isTrue(); }

    @ParameterizedTest @CsvSource({ "Guaritore, 0", "Lupo del branco, 0", "Peccatore, 2" })
    public void testSegnalazioneBoia(String nomeRuolo, int risultato)
    {
        String nome = "Miriam";
        String[][] giocatori = new String[][] { { nome, nomeRuolo }, { "Andrea", "Pazzo" }, { "Sara", "Giullare" } };
        aggiungiGiocatori(giocatori);
        int numeroVoti = 2;
        for(String[] giocatore : giocatori) ballottaggio.incrementaVoti(giocatore[0], numeroVoti);
        ballottaggio.segnalazioneBoia(nome);
        verificaNumeroVoti(nome, numeroVoti);
        for(int i = 1; i < giocatori.length; i++) verificaNumeroVoti(giocatori[i][0], risultato);
    }

    @Test public void testPerdenteBallottaggio()
    {
        String[][] giocatori = new String[][] { { "Davide", "Prete" }, { "Margherita", "Guardia" } };
        aggiungiGiocatori(giocatori);
        int[] numeroVoti = new int[] { 1, 2 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(giocatori[i][0], numeroVoti[i]);
        assertThat(getNomeGiocatorePerdente()).isEqualTo(giocatori[1][0]);
    }

    @Test public void testPareggioBallottaggio()
    {
        String[][] giocatori = new String[][]{ { "Francesco", "Capo branco" }, { "Luca", "Altra guardia" } };
        aggiungiGiocatori(giocatori);
        for(String[] giocatore : giocatori) incrementaVoti(giocatore[0], 1);
        assertThatIllegalArgumentException().isThrownBy(this::getNomeGiocatorePerdente)
            .withMessage("Il villaggio non ha trovato accordo su chi mandare al rogo: non viene bruciato nessuno!");
    }

    @Test public void testCitta()
    {
        String[][] giocatori = new String[][] { { "Davide", "Bocca di rosa" }, { "Dina", "Inquisitore" } };
        aggiungiGiocatori(giocatori);
        assertThat(isCitta(giocatori[0][0])).isTrue();
        assertThat(isCitta(giocatori[1][0])).isFalse();
    }

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

    private void aggiungiGiocatore(String nome, String nomeRuolo) { ballottaggio.aggiungiGiocatore(nome, FACTORY.getRuolo(nomeRuolo)); }

    private void setMercanteAmato()
    {
        Ruolo ruolo = getMercante();
        ruolo.sceltaAngeloCustode();
    }

    private Ruolo getMercante() { return FACTORY.getRuolo("Mercante"); }
    
    

}