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
        aggiungiGiocatore("Aldo");
        aggiungiGiocatore("Giovanni");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void inserimentoGiocatoreNonRiuscito()
    {
        aggiungiGiocatore("Aldo");
        assertThatIllegalArgumentException().isThrownBy(() -> aggiungiGiocatore("Aldo")).withMessage("ERRORE!!! Utente già inserito");
    }

    private void aggiungiGiocatore(String nomeGiocatore) { giocatori.aggiungi(nomeGiocatore); }

}