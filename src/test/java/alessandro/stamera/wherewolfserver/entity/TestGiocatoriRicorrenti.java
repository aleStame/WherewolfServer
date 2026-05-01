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
        assertThat(getNumeroGiocatori()).isEqualTo(2);
    }

    @Test public void inserimentoGiocatoreNonRiuscito()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> aggiungiGiocatore(ESEMPIO_GIOCATORE)).withMessage("ERRORE!!! Utente già inserito");
    }

    @Test public void eliminazioneGiocatore()
    {
        giocatori.elimina(ESEMPIO_GIOCATORE);
        assertThat(getNumeroGiocatori()).isZero();
    }

    @Test public void ordineAlfabetico()
    {
        String[] esempi = { "Adriano", "Davide" };
        aggiungiGiocatore(esempi[0]);
        aggiungiGiocatore(esempi[1]);
        String[] soluzioni = new String[getNumeroGiocatori()];
        for(int i = 0; i < soluzioni.length; i++) soluzioni[i] = giocatori.getNomeGiocatore(i);
        assertThat(soluzioni).isEqualTo(new String[]{ esempi[0], ESEMPIO_GIOCATORE, esempi[1] });
    }

    private void aggiungiGiocatore(String nomeGiocatore) { giocatori.aggiungi(nomeGiocatore); }

    private int getNumeroGiocatori() { return giocatori.getNumeroGiocatori(); }

}