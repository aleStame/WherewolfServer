package alessandro.stamera.wherewolfserver.classi.ruoli;

import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestStrega
{

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "La Strega è l'unico ruolo a poter fermare ogni notte l'attacco delle Creature dell'Ombra, a patto che riesca ad intuire le loro " +
            "mosse e ad agire preventivamente. Cercate di non rivelare la vostra identità se non assolutamente costretti, in quanto siete forse " +
            "l'unico bersaglio nella lista dei Lupi che viene ancora prima della Veggente. Dal momento che il vostro potere protegge sia " +
            "dall'attacco dei Lupi che da quello del Vampiro, ogni turno dovete decidere qual è la priorità maggiore perché i loro bersagli " +
            "saranno diversi e voi potete proteggere un giocatore soltanto. Capire se un giocatore è stato maledetto può risultare determinante " +
            "per leggere correttamente la partita. E' possibile infatti che la Veggente abbia visto un'aura nera su un giocatore che sostiene di " +
            "non possederla e che entrambi stiano dicendo il vero. Soltanto l'uso del vostro potere permetterà di discernere una situazione come " +
            "questa.";
        assertThat(FACTORY.getRuolo("Strega").getConsigli()).isEqualTo(messaggio);
    }

    @Test public void testControlloMedium() { assertThat() }

}