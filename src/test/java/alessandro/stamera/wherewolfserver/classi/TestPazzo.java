package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPazzo
{

    private static final String NOME = "Pazzo";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testBianca() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testControlloMedium() { assertThat(ruolo.controlloMedium()).isEqualTo(BIANCA); }

}