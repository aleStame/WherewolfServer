package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLeprecauno
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Leprecauno(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Leprecauno"); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre creature del Piccolo Popolo. Inoltre, è protetto da tutti i mistici";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isTrue(); }

    @Test public void testCitta() { assertThat(ruolo.isCitta()).isFalse(); }

    @Test public void testCriminale() { assertThat(ruolo.isCriminale()).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}