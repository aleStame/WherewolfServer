package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class) public final class TestUomini
{

    @Mock Uomini ruolo;

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita") public void testEsitoPartita(Partita partita, EsitoPartita esito)
    {
        doCallRealMethod().when(ruolo).getEsitoPartita(partita);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Aurora", "Capo branco" }, { "Giulia", "Nosferatu" } }),
            new Partita(new String[][] { { "Pino", "Capo branco" }, { "Dino", "Assassino" } }),
            new Partita(new String[][] { { "Francesca", "Lupo del branco" }, { "Ermenegildo", "Assassino" } }),
            new Partita(new String[][] { { "Pino", "Giovane lupo" }, { "Adam", "Assassino" } }),
            new Partita(new String[][] { { "Matteo", "Lupo reietto" }, { "Omar", "Assassino" } }),
            new Partita(new String[][] { { "Saverio", "Capo branco" }, { "Desmond", "Assassino" } }),
            new Partita(new String[][] { { "Paola", "Lupo solitario" }, { "Paolo", "Assassino" } })
        };
        return
            Stream.of
            (
                Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], NON_FINITO), Arguments.of(partite[2], NON_FINITO),
                Arguments.of(partite[3], NON_FINITO), Arguments.of(partite[4], NON_FINITO), Arguments.of(partite[5], NON_FINITO),
                Arguments.of(partite[6], NON_FINITO)

            );
    }

}