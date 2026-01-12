package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.*;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestContadino
{

    @Test public void testNome() { assertThat(getContadinoBianco().getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(getContadinoBianco().getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.";
        assertThat(getContadinoNero().getDescrizione()).isEqualTo(descrizione);
    }

    @Test public void testLune() { assertThat(getContadinoNero().getLune()).isEqualTo(1); }

    private Contadino getContadinoBianco() { return new Contadino(BIANCA); }

    private Contadino getContadinoNero() { return new Contadino(NERA); }

}