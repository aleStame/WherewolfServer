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
        aggiungiGiocatore(nome, getPazzoAmato());
        assertThat(isAmatoPresente()).isTrue();
        assertThat(ballottaggio.getNomeAmato()).isEqualTo("Gabriella");
    }

    @Test public void testAmatoAssente()
    {
        aggiungiGiocatore("Lucia", getPazzo());
        assertThat(isAmatoPresente()).isFalse();
    }

    private boolean isAmatoPresente() { return ballottaggio.isAmatoPresente(); }

    private void aggiungiGiocatore(String nome, Ruolo ruolo) { ballottaggio.aggiungiGiocatore(nome, ruolo); }

    private Ruolo getPazzoAmato()
    {
        Ruolo ruolo = getPazzo();
        ruolo.sceltaAngeloCustode();
        return ruolo;
    }

    private Ruolo getPazzo() { return FACTORY.getRuolo("Pazzo"); }

}