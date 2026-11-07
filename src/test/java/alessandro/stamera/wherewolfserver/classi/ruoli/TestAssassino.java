package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAssassino
{

    private static final String NOME = "Assassino";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void tesDescrizione()
    {
        String soluzione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
            "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.";
        verificaStringa(ruolo.getDescrizione(), soluzione);
    }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaVero(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaFalso(ruolo.isLadra()); }

    @Test public void testSpia() { verificaFalso(ruolo.isSpia()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Avete un solo colpo da sparare e dovete decidere molto bene come usarlo. L'obiettivo migliore potrebbe essere una Guardia, poiché " +
            "nessun altro, a parte i Criminali, ha interesse che le guardie vengano eliminate. Di sicuro anche un Lupo sarebbe un ottimo " +
            "bersaglio, anche perché, se capisse chi siete, sarebbe forse lui ad eliminare voi. Scegliete bene il momento in cui aprire gli " +
            "occhi perché, se il Mistico di turno dovesse essere in gioco, dovreste uccidere per forza lui e perdereste così la vostra unica " +
            "occasione.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testGildata() { assertThat(ruolo.gildata()).isEqualTo(FALLITO); }

    @Test public void testGuardiaCorrotta() { verificaFalso(ruolo.isGuardiaCorrotta()); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String soluzione) { assertThat(valore).isEqualTo(soluzione); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}