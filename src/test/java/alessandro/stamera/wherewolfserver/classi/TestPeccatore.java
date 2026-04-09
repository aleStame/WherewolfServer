package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPeccatore
{

    private static final String NOME = "Peccatore";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

}