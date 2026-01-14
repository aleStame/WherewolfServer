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

    @Test public void testContadino() { assertThat(getContadinoBianco().isContadino()).isTrue(); }

    @Test public void testContadinoNormale() { assertThat(getContadinoNero().isContadinoNormale()).isFalse(); }

    @Test public void testContadinoMostro() { assertThat(getContadinoBianco().isContadinoMostro()).isFalse(); }

    private Contadino getContadinoBianco() { return getContadino(BIANCA); }

    private Contadino getContadinoNero() { return getContadino(NERA); }

    private Contadino getContadino(Aura aura) { return new Contadino(aura); }

}