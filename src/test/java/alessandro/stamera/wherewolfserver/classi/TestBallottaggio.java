package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
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