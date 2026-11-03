package alessandro.stamera.wherewolfserver.classi.ruoli;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTraditore
{

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Il vostro obiettivo è quello di far vincere i Lupi, ma è ostacolato dal fatto che non li conoscerete. Per prima cosa dovrete " +
            "quindi cercare di capire chi siano per aiutarli nel loro compito. Ricordatevi che loro conoscono la vostra identità quindi " +
            "potrebbero darvi segnali durante la partita per farsi a loro volta riconoscere. Dal momento che possedete aura bianca, il vostro " +
            "compito dovrà essere quello di confondere le idee al villaggio e dare tempo ai vostri alleati di eliminare bersagli scomodi. E' " +
            "molto probabile che questo vi porterà ad essere scoperti prima della fine della partita, ma la vostra sopravvivenza è inutile " +
            "rispetto a quella dei Lupi che proteggete.";
        assertThat(FACTORY.getRuolo("Traditore").getConsigli()).isEqualTo(messaggio);
    }

    @Test public void testControlloMedium() { assertThat(FACTORY.getRuolo("Traditore").controlloMedium()).isEqualTo(BIANCA); }

}