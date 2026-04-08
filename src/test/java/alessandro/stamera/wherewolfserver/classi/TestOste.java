package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestOste
{

    private static final String NOME = "Oste";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testControlloMedium() { assertThat(ruolo.controlloMedium()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        verificaStringa
        (
            ruolo.getDescrizione(),
    "Ogni mattino se la Veggente quella notte ha scoperto un'aura oscura, il moderatore lo comunica pubblicamente"
        );
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}