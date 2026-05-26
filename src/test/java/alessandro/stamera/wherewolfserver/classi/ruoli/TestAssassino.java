package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAssassino
{

    private static final String NOME = "Assassino";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { testStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void tesDescrizione()
    {
        String soluzione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
            "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.";
        testStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaVero(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaFalso(ruolo.isLadra()); }

    @Test public void testSpia() { verificaFalso(ruolo.isSpia()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void testStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}