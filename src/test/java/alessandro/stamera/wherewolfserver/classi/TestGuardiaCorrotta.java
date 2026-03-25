package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaCorrotta
{

    private static final String NOME = "Guardia corrotta";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        verificaStringa(ruolo.getDescrizione(), "La prima notte riconosce le altre guardie e in seguito gli altri criminali.");
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isTrue(); }

    @Test public void testControlloMedium() { assertThat(ruolo.controlloMedium()).isEqualTo(NERA); }

    //private void verificaAuraNera(Aura aura) { assertThat(aura).i }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}