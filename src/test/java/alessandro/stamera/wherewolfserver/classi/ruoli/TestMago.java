package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestMago
{

    private static final String NOME = "Mago";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        verificaStringa(ruolo.getDescrizione(), "Ogni notte indica un giocatore e scopre se è mistico.");
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaVero(ruolo.isMistico()); }

    @Test public void testContadino() { verificaFalso(ruolo.isContadino()); }

    @Test public void testBardo() { verificaFalso(ruolo.isBardo()); }

    @Test public void testBecchino() { verificaFalso(ruolo.isBecchino()); }

    @Test public void testBracconiere() { verificaFalso(ruolo.isBracconiere()); }

    @Test public void testCacciatore() { verificaFalso(ruolo.isCacciatore()); }

    @Test public void testCacciatoreDiVampiri() { verificaFalso(ruolo.isCacciatoreDiVampiri()); }

    @Test public void testCappuccettoRosso() { verificaFalso(ruolo.isCappuccettoRosso()); }

    @Test public void testEremita() { verificaFalso(ruolo.isEremita()); }

    @Test public void testGuardia() { verificaFalso(ruolo.isGuardia()); }

    @Test public void testGuaritore() { verificaFalso(ruolo.isGuaritore()); }

    @Test public void testMago() { verificaVero(ruolo.isMago()); }

    @Test public void testMedium() { verificaFalso(ruolo.isMedium()); }

    @Test public void testMonaco() { verificaFalso(ruolo.isMonaco()); }

    @Test public void testNonna() { verificaFalso(ruolo.isNonna()); }

    @Test public void testOste() { verificaFalso(ruolo.isOste()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPrete() { verificaFalso(ruolo.isPrete()); }

    @Test public void testSensitiva() { verificaFalso(ruolo.isSensitiva()); }

    @Test public void testVillaggio() { verificaVero(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Il ruolo del Mago è fondamentale per smascherare eventuali impostori. i Mistici sono la difesa principale del villaggio contro " +
            "tutte le Creature dell'Ombra, di conseguenza i ruoli dotati di poteri mistici saranno spesso i più utilizzati come copertura dalle " +
            "Creature dell'Ombra stesse. Oltre ad esse, spesso anche il Pazzo, cercando di attirare su di sé l'attacco dei Lupi, si professerà " +
            "Mistico e lo stesso potrebbero fare Ghoul e Traditore. Usando il vostro potere in combinazione con quello della Veggente dovrete " +
            "capire quali Mistici sono affidabili, quali non lo sono, ma non costituiscono una minaccia reale e quali invece sono un pericolo da " +
            "eliminare. Non dimenticate la possibilità che la Megera sia in gioco. Dal momento che trovate un altro Mistico, e dubitate di lui, " +
            "una strategia valida potrebbe essere quella di indicarlo di nuovo per vedere se il responso del moderatore è cambiato o meno. Se " +
            "sapete che ci sono in gioco ruoli dell'Inquisizione pensateci molto bene prima di esporvi. Per il resto del villaggio l'Inquisizione " +
            "non è un problema, ma per voi e per gli altri Mistici è una minaccia reale quanto le Creature dell'Ombra.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}