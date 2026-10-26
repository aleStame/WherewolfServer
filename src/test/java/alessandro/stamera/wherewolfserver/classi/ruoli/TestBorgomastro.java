package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBorgomastro
{

    private static final String NOME = "Borgomastro";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusato. Può segnalare un giocatore durante il ballottaggio: quel giocatore riceve un " +
            "minimo di voti pari ai ruoli con fazione Città in gioco più uno.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testBorgomastro() { verificaVero(ruolo.isBorgomastro()); }

    @Test public void testMercante() { verificaFalso(ruolo.isMercante()); }

    @Test public void testOratore() { verificaFalso(ruolo.isOratore()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "A meno di trovarvi in un villaggio con diversi Ruoli con Fazione Città, il vostro potere sarà decisivo soprattutto nelle fasi " +
            "finali del gioco. Se riusciste ad arrivare ad un punto con pochi giocatori ancora presenti, allora sì che il vostro contributo " +
            "sarebbe davvero determinante. Pur non essendo un Mistico, il vostro Ruolo risulta particolarmente fastidioso per i Lupi Mannari, " +
            "quindi pensateci bene prima di rivelarvi, soprattutto se sapete di non essere l’unico Ruolo con Fazione Città.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}