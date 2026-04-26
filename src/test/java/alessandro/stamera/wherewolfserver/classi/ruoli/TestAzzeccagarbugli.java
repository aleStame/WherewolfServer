package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TestAzzeccagarbugli
{

    private static final String NOME = "Azzeccagarbugli";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città " +
            "o Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testBoccaDiRosa() { verificaFalso(ruolo.isBoccaDiRosa()); }

    @Test public void testAzzeccagarbugli() { verificaVero(ruolo.isAzzeccagarbugli()); }

    @Test public void testBorgomastro() { verificaFalso(ruolo.isBorgomastro()); }

    @Test public void testMercante() { verificaFalso(ruolo.isMercante()); }

    @Test public void testOratore() { verificaFalso(ruolo.isOratore()); }

    @Test public void testCitta() { verificaVero(ruolo.isCitta()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testSconfittaNessunGiocatore() { verificaPartitaSconfitta(getEsempioPartitaNessunGiocatore()); }

    @Test public void testPartitaNonFinita() { verificaEsitoPartita(getEsempioPartitaNonFinita(), NON_FINITO); }

    @Test public void testPartitaSconfitta()
    {
        Partita partita = mock(Partita.class);
        when(partita.isFinita()).thenReturn(true);
        when(partita.getNumeroGiocatoriVivi()).thenReturn(2);
        when(partita.getNumeroCreatureOmbra()).thenReturn(1);
        verificaPartitaSconfitta(partita);
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaPartitaSconfitta(Partita partita) { verificaEsitoPartita(partita, SCONFITTA); }

    private void verificaEsitoPartita(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
    }

    private Partita getEsempioPartitaNessunGiocatore()
    {
        String[][] giocatori = new String[][] { { "Antonella", "Prete" }, { "Patrizia", "Peccatore" } };
        Partita partita = new Partita(giocatori);
        for(String[] giocatore : giocatori) partita.attaccoLupi("Capo branco", giocatore[0]);
        return partita;
    }

    private Partita getEsempioPartitaNonFinita()
    {
        Partita partita = mock(Partita.class);
        when(partita.isFinita()).thenReturn(false);
        when(partita.getNumeroGiocatoriVivi()).thenReturn(5);
        return partita;
    }

}