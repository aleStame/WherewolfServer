package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestNonna
{

    private static final String NOME = "Nonna";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Non ci sono più creature dell'ombra ed almeno un giocatore è in gioco. Non ci sono Criminali in gioco a fine partita o, se almeno" +
            " un Criminale è ancora in gioco, lo è anche almeno uno tra Guardia e Altra guardia";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}