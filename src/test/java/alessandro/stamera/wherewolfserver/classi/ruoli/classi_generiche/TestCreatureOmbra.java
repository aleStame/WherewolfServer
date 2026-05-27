package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public final class TestCreatureOmbra
{

    @Test public void testCategoria()
    {
        CreatureOmbra ruolo = mock(CreatureOmbra.class);
        doCallRealMethod().when(ruolo).getCategoria();
        assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA);
    }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita") public void testEsitoPartita(Partita partita, EsitoPartita esito)
    {
        CreatureOmbra ruolo = mock(CreatureOmbra.class);
        doCallRealMethod().when(ruolo).getEsitoPartita(partita);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Aurora", "Oratore" }, { "Giulia", "Prete" } }), new Partita(new String[][] { }),
            new Partita(new String[][] { { "Francesca", "Lupo del branco" }, { "Ermenegildo", "Peccatore" } })
        };
        return Stream.of(Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], SCONFITTA), Arguments.of(partite[2], NON_FINITO));
    }

}