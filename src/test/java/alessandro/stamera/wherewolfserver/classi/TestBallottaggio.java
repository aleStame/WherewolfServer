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
        Ruolo ruolo = getPazzo();
        ruolo.sceltaAngeloCustode();
        aggiungiGiocatore("Gabriella", ruolo);
        assertThat(isAmatoPresente()).isTrue();
    }

    @Test public void testAmatoAssente()
    {
        aggiungiGiocatore("Lucia", getPazzo());
        assertThat(isAmatoPresente()).isFalse();
    }

    private boolean isAmatoPresente() { return ballottaggio.isAmatoPresente(); }

    private void aggiungiGiocatore(String nome, Ruolo ruolo) { ballottaggio.aggiungiGiocatore(nome, ruolo); }

    private Ruolo getPazzo() { return FACTORY.getRuolo("Pazzo"); }

}