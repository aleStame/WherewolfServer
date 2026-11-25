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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class) public final class TestCreatureOmbra
{

    @Mock CreatureOmbra ruolo;

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita") public void testEsitoPartita(Partita partita, EsitoPartita esito)
    {
        doCallRealMethod().when(ruolo).getEsitoPartita(partita);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Aurora", "Oratore" }, { "Giulia", "Prete" } }),
            new Partita(new String[][] { { "Francesca", "Lupo del branco" }, { "Ermenegildo", "Peccatore" } }),
            getEsempioVittoriaPosseduto("Altra guardia", "Capo branco"),
            getEsempioVittoriaPosseduto("Altra guardia", "Lupo del branco"),
            getEsempioVittoriaPosseduto("Altra guardia", "Lupo reietto"),
            getEsempioVittoriaPosseduto("Altra guardia", "Lupo solitario")
        };
        return
            Stream.of
            (
                Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], NON_FINITO), Arguments.of(partite[2], VITTORIA),
                Arguments.of(partite[3], VITTORIA), Arguments.of(partite[4], VITTORIA), Arguments.of(partite[5], VITTORIA)
            );
    }

    private static Partita getEsempioVittoriaPosseduto(String nomeRuolo, String tipoLupo)
    {
        String nomePosseduto = "Franco", nomeVittima = "Giulio", nomeLupo = "Ciccio";
        Partita partita = new Partita(new String[][] { { nomePosseduto, "Posseduto" }, { nomeLupo, tipoLupo }, { "Pina", "Megera" }, { nomeVittima, nomeRuolo } });
        partita.attaccoLupi(tipoLupo, nomePosseduto);
        partita.passaPosseduto(nomeVittima);
        partita.terminaNotte();
        partita.incrementaVoti(nomeLupo, 2);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeLupo, 2);
        partita.terminaBallottaggio();
        partita.terminaNotte();
        return partita;
    }

}