package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGhoul
{

    private static final String NOME = "Ghoul";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce la Megera, inoltre apre gli occhi nel turno del Vampiro e del Nosferatu. Se il Vampiro o il Nosferatu " +
            "dovessero essere uccisi durante il proprio turno, al loro posto morirà il Nosferatu. Vince se vince uno dei due.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testAura() { verificaAuraBianca(ruolo.getAura()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testGhoul() { assertThat(ruolo.isGhoul()).isTrue(); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLeprecauno() { verificaFalso(ruolo.isLeprecauno()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @ParameterizedTest @MethodSource("getEsempioPartita") public void testVittoria(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
        FACTORY.annullaSegnalazioni();
    }

    private static Stream<Arguments> getEsempioPartita()
    {
        String[][] giocatori = new String[][] { { "Angelo", "Nosferatu" }, { "Raf", "Ghoul" }, { "Aurora", "Capo branco" }, { "Giulia", "Prete" } };
        Partita[] partite =
            new Partita[] { new Partita(giocatori), new Partita(new String[][] { { "Luca", "Capo branco" }, { "Lucia", "Oste" } }) };
        setPartitaVinta(partite[0], giocatori[2][1], giocatori[3][0], giocatori[2][0]);
        return Stream.of(Arguments.of(partite[0], VITTORIA), Arguments.of(partite[1], SCONFITTA));
    }

    private static void setPartitaVinta(Partita partita, String tipoLupo, String nomeVittima, String nomeLupo)
    {
        partita.attaccoLupi(tipoLupo, nomeVittima);
        partita.progenizzazioneNosferatu(nomeVittima);
        partita.terminaNotte();
        int numeroVoti = 3;
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaBallottaggio();
        partita.terminaNotte();
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}