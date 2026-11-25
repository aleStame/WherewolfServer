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
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
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
            getEsempioVittoriaPosseduto("Altra guardia", "Lupo solitario"),
            getEsempioSconfittaConContadinoLupo("Altra guardia", "Capo branco"),
            getEsempioSconfittaConContadinoLupo("Altra guardia", "Lupo del branco"),
            getEsempioSconfittaConContadinoLupo("Altra guardia", "Lupo reietto"),
            getEsempioSconfittaConContadinoLupo("Altra guardia", "Lupo solitario")
        };
        return
            Stream.of
            (
                Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], NON_FINITO), Arguments.of(partite[2], VITTORIA),
                Arguments.of(partite[3], VITTORIA), Arguments.of(partite[4], VITTORIA), Arguments.of(partite[5], VITTORIA),
                Arguments.of(partite[6], NON_FINITO), Arguments.of(partite[7], NON_FINITO), Arguments.of(partite[8], NON_FINITO),
                Arguments.of(partite[9], NON_FINITO)
            );
    }

    private static Partita getEsempioVittoriaPosseduto(String nomeRuolo, String tipoLupo)
    {
        String nomePosseduto = "Franco", nomeVittima = "Giulio", nomeLupo = "Ciccio";
        String[][] giocatori =
            new String[][] { { nomePosseduto, "Posseduto" }, { nomeLupo, tipoLupo }, { "Pina", "Megera" }, { nomeVittima, nomeRuolo } };
        Partita partita = new Partita(giocatori);
        passaPosseduto(tipoLupo, partita, nomePosseduto, nomeVittima);
        eliminaGiocatoreBallottaggio(partita, nomeLupo);
        return partita;
    }

    private static Partita getEsempioSconfittaConContadinoLupo(String nomeRuolo, String tipoLupo)
    {
        String nomeContadino = "Franco", nomeVittima = "Giulio", nomeLupo = "Ciccio";
        String[][] giocatori =
            new String[][] { { nomeContadino, "Contadino discendente dei lupi" }, { nomeLupo, tipoLupo }, { nomeVittima, nomeRuolo } };
        Partita partita = new Partita(giocatori);
        assertThatIllegalArgumentException().isThrownBy(() -> partita.attaccoLupi(tipoLupo, nomeContadino));
        partita.terminaNotte();
        eliminaGiocatoreBallottaggio(partita, nomeLupo);
        return partita;
    }

    private static void passaPosseduto(String tipoLupo, Partita partita, String nomePosseduto, String nomeVittima)
    {
        partita.attaccoLupi(tipoLupo, nomePosseduto);
        partita.passaPosseduto(nomeVittima);
        partita.terminaNotte();
    }

    private static void eliminaGiocatoreBallottaggio(Partita partita, String nomeLupo)
    {
        partita.incrementaVoti(nomeLupo, 2);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeLupo, 2);
        partita.terminaBallottaggio();
        partita.terminaNotte();
    }

}