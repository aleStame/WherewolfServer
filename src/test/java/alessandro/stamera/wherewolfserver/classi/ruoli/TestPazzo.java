package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.SCONFITTA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestPazzo
{

    private static final String NOME = "Pazzo";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(Fazione.NESSUNA); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(Categoria.NESSUNA); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "Se viene eliminato perché ucciso dai lupi mannari, la notte successiva il loro attacco sarà annullato. Vince se viene " +
            "eliminato dall'attacco dei lupi mannari";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(1); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLeprecauno() { verificaFalso(ruolo.isLeprecauno()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testMegera() { verificaFalso(ruolo.isMegera()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testPazzo() { assertThat(ruolo.isPazzo()).isTrue(); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "C'è un solo motivo perché i Lupo vorranno attaccarvi: dovranno credere veramente che siate chi dite di essere, e chi dite di essere " +
            "dovrà essere per loro un nemico prioritario. Sicuramente la scelta migliore è quella di fingervi Veggente o Strega. Ma attenzione: " +
            "se i Lupi capiranno che state bluffando, vi trascureranno e vi lasceranno in vita, mentre se non sarete credibili per il villaggio " +
            "finirete sul rogo. In entrambi i casi non raggiungerete il vostro scopo. Il Pazzo è un ruolo molto difficile da giocare. Dovrete " +
            "vincere ingannando tutti, i Lupi e il villaggio. I primi sopratutto dovranno essere davvero convinti della vostra identità, perché " +
            "attaccandovi perderebbero due turni preziosi durante la notte. Riuscire a vincere con questo ruolo spesso risulta tanto arduo quanto " +
            "gratificante.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    @Test public void testSconfittaPerAssassinio()
    {
        String nomeVittima = "Cooper";
        Partita partita = new Partita(new String[][] { { "Bob", "Assassino" }, { "Laura", "Bocca di rosa" }, { nomeVittima, "Pazzo" } });
        partita.attaccoAssassino(nomeVittima);
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(SCONFITTA);
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaStringa(String descrizione, String risultato)
    {
        assertThat(descrizione).isEqualTo(risultato);
    }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}