package alessandro.stamera.wherewolfserver.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class TestGiocatoriRicorrenti
{

    private static final String ESEMPIO_GIOCATORE = "Aldo";

    private GiocatoriRicorrenti giocatori;

    @BeforeEach public void setUp()
    {
        giocatori = new GiocatoriRicorrenti();
        aggiungiGiocatore(ESEMPIO_GIOCATORE);
    }

    @Test public void inserimentoGiocatoreRiuscito()
    {
        aggiungiGiocatore("Giovanni");
        assertThat(giocatori.getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void inserimentoGiocatoreNonRiuscito()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> aggiungiGiocatore(ESEMPIO_GIOCATORE)).withMessage("ERRORE!!! Utente già inserito");
    }

    private void aggiungiGiocatore(String nomeGiocatore) { giocatori.aggiungi(nomeGiocatore); }

}