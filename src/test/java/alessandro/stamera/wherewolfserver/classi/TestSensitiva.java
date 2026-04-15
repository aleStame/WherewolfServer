package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestSensitiva
{

    private static final String NOME = "Sensitiva";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Se è in gioco, lo è al posto della VEGGENTE. Ogni notte indica un giocatore (compresa sé stessa) e scopre se possiede fazione " +
            "Villaggio. I giocatori maledetti hanno fazione Maledetto solo ai fini delle condizioni di fine gioco, quindi vengono visti dalla " +
            "Sensitiva con la loro fazione originale";
        assertThat(ruolo.getDescrizione()).isEqualTo(descrizione);
    }

}