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

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestViaggiatore
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo("Viaggiatore"); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "La vostra condizione di vittoria è unica e differente da tutte le altre: dovete rimanere vivo. Se non fino alla fine del gioco, " +
            "almeno quanto basta da poter lasciare il villaggio. La scelta più importante sarà quella di decidere se rivelare o meno la vostra " +
            "identità al villaggio. Quello del Viaggiatore è uno dei ruoli verso cui le reazioni saranno più disparate: a volte vi crederanno e vi " +
            "trascureranno del tutto, a volte vi bruceranno subito poiché, non avendo schieramento, potreste comunque essere d’intralcio. Spesso " +
            "potrebbe risultare più efficace mentire sulla vostra identità, fingendo di essere un ruolo che nessuno ha interesse ad eliminare in " +
            "quel momento. Dovete solo capire quale può essere quel ruolo in quel contesto specifico.";
        assertThat(ruolo.getConsigli()).isEqualTo(messaggio);
    }

    @Test public void testControlloMedium() { assertThat(ruolo.controlloMedium()).isEqualTo(BIANCA); }

    @ParameterizedTest @MethodSource("getEsempiPartite")
    public void testEsitoPartitaViaggiatore(Partita partita, EsitoPartita esito)
    {
        assertThat(partita.isEliminato("Buffy")).isTrue();
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private static Stream<Arguments> getEsempiPartite()
    {
        return Stream.of
        (
            Arguments.of(getPartitaSeiGiocatoriViaggiatoreNonEliminatoVincitore(), VITTORIA),
            Arguments.of(getPartitaSetteGiocatori(), VITTORIA), Arguments.of(getPartitaSeiGiocatoriViaggiatoreEliminatoSconfitto(), SCONFITTA),
            Arguments.of(getPartitaSeiGiocatoriViaggiatoreEliminatoVincitore(), VITTORIA)
        );
    }

    private static Partita getPartitaSeiGiocatoriViaggiatoreNonEliminatoVincitore()
    {
        String[][] giocatori =
        {
            { "Buffy", "Viaggiatore" }, { "Oz", "Capo branco" }, { "Spike", "Assassino" }, { "Nathan", "Prete" }, { "Xander", "Guardia" },
            { "Willow", "Guaritore" }
        };
        Partita partita = new Partita(giocatori);
        partita.terminaNotte();
        return partita;
    }

    private static Partita getPartitaSetteGiocatori()
    {
        String nomeAssassino = "Spike", tipoLupo = "Capo branco", nomeMedium = "Giles";
        String[][] giocatori =
        {
            { "Buffy", "Viaggiatore" }, { "Oz", tipoLupo }, { nomeAssassino, "Assassino" }, { "Nathan", "Prete" }, { "Xander", "Guardia" },
            { "Willow", "Guaritore" }, { nomeMedium, "Medium" }
        };
        Partita partita = new Partita(giocatori);
        int numeroVoti = 6;
        partita.incrementaVoti(nomeAssassino, numeroVoti);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeAssassino, numeroVoti);
        partita.terminaBallottaggio();
        partita.attaccoLupi(tipoLupo, nomeMedium);
        partita.terminaNotte();
        partita.terminaNotte();
        return partita;
    }

    private static Partita getPartitaSeiGiocatoriViaggiatoreEliminatoSconfitto()
    {
        String nomeViaggiatore = "Buffy";
        String[][] giocatori =
        {
            { nomeViaggiatore, "Viaggiatore" }, { "Oz", "Capo branco" }, { "Spike", "Assassino" }, { "Nathan", "Prete" },
            { "Xander", "Guardia" }, { "Willow", "Guaritore" }
        };
        Partita partita = new Partita(giocatori);
        partita.attaccoAssassino(nomeViaggiatore);
        partita.terminaNotte();
        return partita;
    }

    private static Partita getPartitaSeiGiocatoriViaggiatoreEliminatoVincitore()
    {
        String nomeViaggiatore = "Buffy";
        String[][] giocatori =
        {
            { nomeViaggiatore, "Viaggiatore" }, { "Oz", "Capo branco" }, { "Spike", "Assassino" }, { "Nathan", "Prete" },
            { "Xander", "Guardia" }, { "Willow", "Guaritore" }
        };
        Partita partita = new Partita(giocatori);
        partita.attaccoAssassino(nomeViaggiatore);
        partita.guarisci(nomeViaggiatore);
        partita.terminaNotte();
        return partita;
    }

}