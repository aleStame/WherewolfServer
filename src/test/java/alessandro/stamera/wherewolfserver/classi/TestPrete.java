package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPrete
{

    private static final String NOME = "Prete";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua il Peccatore e scopre se Bocca di rosa e il Posseduto sono in gioco. È protetto dal Posseduto e, se viene " +
            "attaccato, viene avvisato e gli fa perdere tutti i poteri.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}