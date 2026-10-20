package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.UOMINI;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VILLAGGIO;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardia
{

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = new Guardia(null, null, null); }

    @Test public void testFazione() { assertThat(getFazione()).isEqualTo(VILLAGGIO); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(UOMINI); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testEremita() { verificaFalso(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaVero(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testMago() { verificaFalso(ruolo.isMago()); }

    @Test public void testMedium() { verificaFalso(ruolo.isMedium()); }

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPrete() { verificaFalso(ruolo.isPrete()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Se non vi sono Criminali, avrete una partita più tranquilla: avere qualcuno che conosce il vostro Ruolo è sicuramente molto utile " +
            "durante le Votazioni e le difese. Attenzione solo a non essere scambiati per i due Lupi Mannari! Il Ruolo delle Guardie diventa " +
            "molto più interessante, e delicato, qualora vi siano in gioco dei Criminali. In questo caso, la sopravvivenza di una delle due " +
            "Guardie diventa di vitale importanza per la sicurezza del villaggio. Cercate di non rivelarvi subito: più rimarrete nascosti " +
            "maggiori saranno le possibilità di salvarvi dall’Assassino e di catturare il Capo Gilda. Rivelarsi Guardia può essere pericoloso " +
            "anche se si pensa che possa essere presente il Vampiro. Se dovesse infatti trasformare una delle Guardie, avrebbe modo di sapere " +
            "facilmente chi sono anche le altre.";
        assertThat(ruolo.getConsigli()).isEqualTo(messaggio);
    }

    private Fazione getFazione() { return ruolo.getFazione(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}