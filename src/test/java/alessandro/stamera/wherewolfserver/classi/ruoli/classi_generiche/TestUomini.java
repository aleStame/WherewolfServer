package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public final class TestUomini
{

    @Test public void testCategoria()
    {
        Uomini ruolo = mock(Uomini.class);
        doCallRealMethod().when(ruolo).getCategoria();
        assertThat(ruolo.getCategoria()).isEqualTo(UOMINI);
    }

    @ParameterizedTest @MethodSource("getEsempiEsitiPartita") public void testEsitoPartita(Partita partita, EsitoPartita esito)
    {
        Uomini ruolo = mock(Uomini.class);
        doCallRealMethod().when(ruolo).getEsitoPartita(partita);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private static Stream<Arguments> getEsempiEsitiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Aurora", "Capo branco" }, { "Giulia", "Nosferatu" } }), new Partita(new String[][] { }),
            new Partita(new String[][] { { "Francesca", "Lupo del branco" }, { "Ermenegildo", "Peccatore" } }),
        };
        return Stream.of(Arguments.of(partite[0], SCONFITTA), Arguments.of(partite[1], SCONFITTA), Arguments.of(partite[2], NON_FINITO));
    }

}