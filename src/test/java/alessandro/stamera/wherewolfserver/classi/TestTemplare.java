package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTemplare
{

    private static final String NOME = "Templare";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testInquisizione() { assertThat(ruolo.isInquisizione()).isTrue(); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testInquisitore() { verificaFalso(ruolo.isInquisitore()); }

    @Test public void testTemplare() { assertThat(ruolo.isTemplare()).isTrue(); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}