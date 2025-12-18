package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class TestGiocatoriRicorrenti
{

    private GiocatoriRicorrenti giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriRicorrenti(); }

    @Test public void inserimentoGiocatoreRiuscito()
    {
        giocatori.aggiungi("Aldo");
        giocatori.aggiungi("Giovanni");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void inserimentoGiocatoreNonRiuscito()
    {
        giocatori.aggiungi("Aldo");
        assertThatIllegalArgumentException().isThrownBy(() -> giocatori.aggiungi("Aldo")).withMessage("ERRORE!!! Utente già inserito");
    }

}