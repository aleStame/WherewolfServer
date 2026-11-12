package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoia
{

    private static final String NOME = "Boia";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte viene individuato dall'Inquisitore. Se non è accusato, può segnalare un giocatore durante il ballottaggio: se è un " +
            "mistico o una creatura dell'ombra, i voti di tutti gli altri accusati vengono azzerati alla fine della votazione";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testBoia() { verificaVero(ruolo.isBoia()); }

    @Test public void testInquisitore() { verificaFalso(ruolo.isInquisitore()); }

    @Test public void testTemplare() { verificaFalso(ruolo.isTemplare()); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Come tutti i Ruoli che giocano per la Vittoria degli Uomini ma che hanno Aura Oscura, avete la difficoltà aggiuntiva di dover rendere " +
            "conto di un’eventuale indagine che la Veggente potrebbe fare su di voi. L’unico che potrebbe scagionarvi è l’Inquisitore, ma anche " +
            "lui (come voi) potrebbe non godere della collaborazione del villaggio, dato il vostro obiettivo.\nLa strategia migliore potrebbe " +
            "essere quella di giocare un profilo basso e scoprire il più possibile, prima di essere messi alle strette. Per certo l’Inquisitore, " +
            "conoscendo la vostra identità, cercherà di comunicare con voi durante il gioco: siate quindi bene attenti ai messaggi che vi mandano " +
            "gli altri giocatori. Nella vostra lotta contro i Mistici, cercate anche di ottenere tutte le informazioni che possono darvi prima di " +
            "metterli al rogo: in fondo, possono tornare utili alla vostra causa.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}