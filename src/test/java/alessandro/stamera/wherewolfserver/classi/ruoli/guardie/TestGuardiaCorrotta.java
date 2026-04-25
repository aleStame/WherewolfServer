package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
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

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isTrue(); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}