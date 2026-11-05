package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
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

    @Test public void testVittoriaPartitaSeiGiocatori()
    {
        String nomeViaggiatore = "Buffy";
        String[][] giocatori =
        {
            { nomeViaggiatore, "Viaggiatore" }, { "Oz", "Capo branco" }, { "Spike", "Assassino" }, { "Nathan", "Prete" }, { "Xander", "Guardia" },
            { "Willow", "Strega" }
        };
        Partita partita = new Partita(giocatori);
        partita.terminaNotte();
        assertThat(partita.isEliminato(nomeViaggiatore)).isTrue();
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

    @Test public void testVittoriaPartitaSetteGiocatori()
    {
        String nomeViaggiatore = "Buffy", nomeAssassino = "Spike", tipoLupo = "Capo branco", nomeMedium = "Giles";
        String[][] giocatori =
        {
            { nomeViaggiatore, "Viaggiatore" }, { "Oz", tipoLupo }, { nomeAssassino, "Assassino" }, { "Nathan", "Prete" }, { "Xander", "Guardia" },
            { "Willow", "Strega" }, { nomeMedium, "Medium" }
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
        assertThat(partita.isEliminato(nomeViaggiatore)).isTrue();
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

}