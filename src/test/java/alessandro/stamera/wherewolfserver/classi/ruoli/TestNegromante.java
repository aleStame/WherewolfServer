package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NEGROMANTE;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestNegromante
{

    private static final String NOME = "Negromante";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NEGROMANTE); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(NERA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte individua la Megera, riconosce il Becchino e indica due giocatori che diventano maledetti fino a che il Negromante è" +
            " in gioco. Se all'inizio del giorno sono stati maledetti due o più giocatori maledetti, il Moderatore lo comunica pubblicamente. Il " +
            "mattino successivo, se il Negromante è ancora in gioco, tutti i giocatori di fazione diversa da Negromante vengono eliminati.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testMistico() { verificaVero(ruolo.isMistico()); }

    @Test public void testBecchino() { assertThat(ruolo.isBecchino()).isFalse(); }

    @Test public void testNegromante() { verificaVero(ruolo.isNegromante()); }

    @Test public void testFazioneNegromante() { verificaVero(ruolo.isFazioneNegromante()); }

    @ParameterizedTest @MethodSource({ "getEsempiPartita" })
    public void testEsitoPartita(Partita partita, EsitoPartita esito) { assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private static Stream<Arguments> getEsempiPartita()
    {
        String nome = "Antonella", ruolo = "Negromante";
        return Stream.of
        (
            Arguments.of(new Partita(new String[][] { { nome, ruolo } }), VITTORIA),
            Arguments.of(new Partita(new String[][] { { nome, ruolo }, { "Valerio", "Capo branco" } }), SCONFITTA)
        );
    }

}