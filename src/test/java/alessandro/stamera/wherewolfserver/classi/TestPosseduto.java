package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.POSSEDUTO;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPosseduto
{

    private static final String NOME = "Posseduto";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(POSSEDUTO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @Test public void testAura() { verificaAuraOscura(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraOscura(ruolo.controlloMedium()); }

    private void verificaAuraOscura(Aura aura) { assertThat(aura).isEqualTo(NERA); }

}