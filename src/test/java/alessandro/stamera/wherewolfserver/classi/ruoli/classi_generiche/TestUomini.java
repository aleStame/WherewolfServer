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

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita")
    public void testEsitoPartita(Partita partita, EsitoPartita esito)
    {
        doCallRealMethod().when(ruolo).getEsitoPartita(partita);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartitaProgenie")
    public void testEsitoPartitaProgenie(Partita partita, EsitoPartita esito)
    {
        System.out.println(esito);
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
            new Partita(new String[][] { { "Paola", "Lupo solitario" }, { "Paolo", "Assassino" } }),
            new Partita(new String[][] { { "Anna", "Capo branco" }, { "Romana", "Traditore" } }),
            new Partita(new String[][] { { "Fabrizio", "Lupo del branco" }, { "Paola", "Traditore" } }),
            new Partita(new String[][] { { "Anna", "Giovane lupo" }, { "Romana", "Traditore" } }),
            new Partita(new String[][] { { "Ezio", "Lupo reietto" }, { "Enzo", "Traditore" } }),
            new Partita(new String[][] { { "Claudia", "Lupo solitario" }, { "Massimo", "Traditore" } }),
            new Partita(new String[][] { { "John", "Vampiro" }, { "Jack", "Ghoul" } }),
            new Partita(new String[][] { { "Hal", "Nosferatu" }, { "Guy", "Ghoul" } }),
        };
        return
            Stream.of
            (
                Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], NON_FINITO), Arguments.of(partite[2], NON_FINITO),
                Arguments.of(partite[3], NON_FINITO), Arguments.of(partite[4], NON_FINITO), Arguments.of(partite[5], NON_FINITO),
                Arguments.of(partite[6], NON_FINITO), Arguments.of(partite[7], SCONFITTA), Arguments.of(partite[8], SCONFITTA),
                Arguments.of(partite[9], SCONFITTA), Arguments.of(partite[10], SCONFITTA), Arguments.of(partite[11], SCONFITTA),
                Arguments.of(partite[12], SCONFITTA), Arguments.of(partite[13], SCONFITTA)
            );
    }

    private static Stream<Arguments> getEsempiEsitiPartitaProgenie()
    {
        Partita[] partite = new Partita[3];
        String[] nomiVittime = { "Dario", "Elena", "Alessandro", "Giuseppina" };
        partite[0] = new Partita(new String[][] { { "Mario", "Vampiro" }, { nomiVittime[0], "Giullare" } });
        partite[0].attaccoVampiro(nomiVittime[0]);
        String nomeVampiro = "Alberto";
        partite[1] = new Partita(new String[][] { { nomiVittime[1], "Peccatore" }, { nomiVittime[2], "Pazzo" }, { nomeVampiro, "Vampiro" } });
        partite[1].attaccoVampiro(nomiVittime[1]);
        partite[1].attaccoVampiro(nomiVittime[2]);
        partite[1].incrementaVoti(nomeVampiro, 2);
        partite[1].terminaVotazioni();
        partite[1].incrementaVoti(nomeVampiro, 2);
        partite[1].terminaBallottaggio();
        partite[2] = new Partita(new String[][] { { "Michela", "Vampiro" }, { nomiVittime[3], "Pazzo" }, { "Maria", "Assassino" } });
        partite[2].attaccoVampiro(nomiVittime[3]);
        return Stream.of(Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], SCONFITTA), Arguments.of(partite[2], NON_FINITO));
    }

}