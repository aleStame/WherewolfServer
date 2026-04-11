package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPiccoloPopolo
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new PiccoloPopolo(null, null, null); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaVero(ruolo.isMistico()); }

    @Test public void testSegnalazioneInquisitore()
    {
        assertThat(isAccusato()).isFalse();
        ruolo.segnalazioneInquisitore();
        verificaVero(isAccusato());
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private boolean isAccusato() { return ruolo.isAccusato(); }

}