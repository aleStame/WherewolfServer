package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestBoccaDiRosa
{

    private static final String NOME = "Bocca di rosa";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testBoccaDiRosa() { verificaVero(ruolo.isBoccaDiRosa()); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusata. In ogni votazione i voti che Bocca di rosa riceve vengono dimezzati, " +
            "arrotondando per eccesso.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAzzeccagarbugli() { verificaFalso(ruolo.isAzzeccagarbugli()); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    @Test public void testBorgomastro() { verificaFalso(ruolo.isBorgomastro()); }

    @Test public void testMercante() { verificaFalso(ruolo.isMercante()); }

    @Test public void testOratore() { verificaFalso(ruolo.isOratore()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Come il Peccatore, siete un Ruolo dall’aura nera che gioca però per la vittoria degli Uomini. A differenza di lui, il vostro potere " +
            "vi rende abbastanza protetti nelle votazioni, ma attenti a non farci troppo affidamento perché, se in troppi dovessero convincersi " +
            "che siete un Lupo, potreste rischiare di finire ugualmente al rogo. Essendo un ruolo con fazione Città, diversi altri giocatori con " +
            "la stessa fazione potrebbero beneficiare della vostra presenza e usare i loro poteri per proteggervi. Questo potrebbe essere un " +
            "motivo in più per rivelarvi apertamente.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}