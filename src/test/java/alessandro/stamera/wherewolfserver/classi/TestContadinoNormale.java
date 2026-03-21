package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class TestContadinoNormale
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo("Contadino normale"); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo("Contadino"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testContadino() { verificaVero(ruolo.isContadino()); }

    @Test public void testContadinoNormale() { verificaVero(ruolo.isContadinoNormale()); }

    @Test public void testContadinoMostro() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoEroe() { verificaFalso(ruolo.isContadinoMostro()); }

    @Test public void testContadinoLupo() { verificaFalso(ruolo.isContadinoLupo()); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}