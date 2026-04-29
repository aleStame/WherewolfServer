package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBallottaggio
{

    private Ballottaggio ballottaggio;

    @BeforeEach public void setUp() { ballottaggio = new Ballottaggio(); }

    @Test public void testAmatoPresente()
    {
        String nome = "Gabriella";
        aggiungiGiocatore(nome, getMercanteAmato());
        assertThat(isAmatoPresente()).isTrue();
        assertThat(ballottaggio.getNomeAmato()).isEqualTo(nome);
    }

    @Test public void testAmatoAssente()
    {
        aggiungiGiocatore("Lucia", getMercante());
        assertThat(isAmatoPresente()).isFalse();
    }

    @Test public void testNessunaSegnalazione() { assertThat(ballottaggio.isSegnalazioneAssente()).isTrue(); }

    @ParameterizedTest @CsvSource({ "Guaritore, 0", "Lupo del branco, 0", "Peccatore, 2" })
    public void testSegnalazioneBoia(String nomeRuolo, int risultato)
    {
        String nome = "Miriam";
        String[][] giocatori = new String[][] { { nome, nomeRuolo }, { "Andrea", "Pazzo" }, { "Sara", "Giullare" } };
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], FACTORY.getRuolo(giocatore[1]));
        int numeroVoti = 2;
        for(String[] giocatore : giocatori) ballottaggio.incrementaVoti(giocatore[0], numeroVoti);
        ballottaggio.segnalazioneBoia(nome);
        assertThat(ballottaggio.getNumeroVoti(giocatori[0][0])).isEqualTo(numeroVoti);
        for(int i = 1; i < giocatori.length; i++) assertThat(ballottaggio.getNumeroVoti(giocatori[i][0])).isEqualTo(risultato);
    }

    private boolean isAmatoPresente() { return ballottaggio.isAmatoPresente(); }

    private void aggiungiGiocatore(String nome, Ruolo ruolo) { ballottaggio.aggiungiGiocatore(nome, ruolo); }

    private Ruolo getMercanteAmato()
    {
        Ruolo ruolo = getMercante();
        ruolo.sceltaAngeloCustode();
        return ruolo;
    }

    private Ruolo getMercante() { return FACTORY.getRuolo("Mercante"); }
    
    

}