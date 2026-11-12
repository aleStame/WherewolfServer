package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiullare
{

    private static final String NOME = "Giullare";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Se viene messo al rogo, il giorno successivo il rogo sarà annullato a prescindere dai voti e dai poteri utilizzati. Vince se " +
            "viene messo al rogo.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { assertThat(ruolo.isGiullare()).isTrue(); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLeprecauno() { verificaFalso(ruolo.isLeprecauno()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Di tutti i ruoli del gioco, il Giullare è sicuramente uno dei più anomali. Mentre gli altri ruoli, sia che giochino per gli Uomini " +
            "sia che giochino per l'Ombra, cercheranno di conquistarsi la fiducia degli altri giocatori, voi dovrete fare esattamente l'opposto! " +
            "Non potrete dire di essere una Creatura dell'Ombra per essere mandati al rogo, perché nessuna di loro lo farebbe mai. Il modo " +
            "migliore è sicuramente quello di difendere la vostra posizione, ma in maniera poco credibile cercando di destare sospetti ma sempre " +
            "senza darlo a vedere. Nel fare questo, cercate di non sembrare un bersaglio troppo interessante per i Lupi perché, se saranno loro " +
            "ad uccidervi, avrete perso la partita. Ricordate anche che, in ogni votazione, avrete sempre un voto sicuro per essere accusato, il " +
            "vostro!";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}