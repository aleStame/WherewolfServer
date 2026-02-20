package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEremita
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Eremita(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Eremita"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test
    public void testDescrizione() { verificaStringa(ruolo.getDescrizione(), "È protetto dalle creature dell'ombra"); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}