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

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Quello del Negromante è un Ruolo difficile. Dovrete cercare di tenere un profilo basso e di evitare di essere indagati dalla " +
            "Veggente. Infatti, anche nel caso riusciste a bluffare un alibi sul momento, probabilmente i giocatori si ricorderebbero una volta " +
            "che il rituale dovesse partire. L’unico vantaggio che avete, è che spesso i giocatori tenderanno a dimenticarsi della minaccia che " +
            "costituite fino a che non sarà troppo tardi. Se doveste arrivare a far partire il rituale, fate di tutto per cercare di rimanere " +
            "vivi, anche a costo di dire di essere una Creatura dell’Ombra differente pur di allontanare da voi ogni sospetto. In fondo, in quel " +
            "momento, vi basterà guadagnare un giorno soltanto.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

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