package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class TestGiocatoriRicorrenti
{

    @Test public void inserimentoGiocatoreRiuscito()
    {
        GiocatoriRicorrenti giocatori = new GiocatoriRicorrenti();
        giocatori.aggiungi("Aldo");
        giocatori.aggiungi("Giovanni");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void inserimentoGiocatoreNonRiuscito()
    {
        GiocatoriRicorrenti giocatori = new GiocatoriRicorrenti();
        giocatori.aggiungi("Aldo");
        assertThatIllegalArgumentException().isThrownBy(() -> giocatori.aggiungi("Aldo")).withMessage("ERRORE!!! Utente già inserito.");
    }

}