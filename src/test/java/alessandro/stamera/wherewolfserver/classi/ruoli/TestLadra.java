package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestLadra
{

    private static final String NOME = "Ladra";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
            "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testCriminale() { verificaVero(ruolo.isCriminale()); }

    @Test public void testAssassino() { verificaFalso(ruolo.isAssassino()); }

    @Test public void testCapoGilda() { verificaFalso(ruolo.isCapoGilda()); }

    @Test public void testLadra() { verificaVero(ruolo.isLadra()); }

    @Test public void testSpia() { verificaFalso(ruolo.isSpia()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Il vostro ruolo è molto delicato. Essendo uno dei Criminali con aura bianca, siete più difficilmente individuabili e sarà per voi " +
            "più facile confondervi tra i vari abitanti del Villaggio. L'unico modo per essere scoperta, è quello di usare il vostro potere " +
            "nel turno di un Mistico ancora in gioco, quindi dovreste cercare di utilizzarlo solo se pensate che uno dei Mistici in gioco stia " +
            "bluffando. Anche la vostra protezione durante la notte è delicata: è vero che funziona una volta soltanto, ma le Creature " +
            "dell'Ombra questo non lo sanno e possono pensare di aver attaccato qualcuno che sia protetto continuamente e questo può sicuramente " +
            "giocare a vostro vantaggio.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}