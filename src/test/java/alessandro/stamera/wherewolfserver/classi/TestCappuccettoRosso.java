package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestCappuccettoRosso
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new CappuccettoRosso(); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), "Cappuccetto rosso"); }

    @Test public void testAura() { assertThat(ruolo.getAura()).isEqualTo(BIANCA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Finché la Nonna è in gioco (anche se essa riceve il tratto Non morto o diventa il Posseduto) e non si è trasformata in Lupo, " +
            "Cappuccetto rosso è protetta dall'attacco dei Lupi. Se l'ultimo Lupo in gioco (sia esso l'ultimo Lupo del Branco o il Lupo " +
            "solitario) attacca Cappuccetto rosso, quest'ultima apre gli occhi e lo riconosce, anche se fosse Romeo o protetta dalla Strega";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { assertThat(ruolo.isMistico()).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}