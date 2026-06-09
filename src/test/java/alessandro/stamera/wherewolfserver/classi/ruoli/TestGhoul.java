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

    @ParameterizedTest @MethodSource("getEsempiPartita") public void testEsempioPartita(Partita partita, EsitoPartita esito)
    {
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(esito);
        FACTORY.annullaSegnalazioni();
    }

    private static Stream<Arguments> getEsempiPartita()
    {
        Partita[] partite = new Partita[]
        {
            new Partita(new String[][] { { "Tony", "Nosferatu" }, { "Steve", "Capo branco" }, { "Natasha", "Prete" }, { "Wanda", "Peccatore" } }),
            new Partita
            (
                new String[][]
                {
                    { "Katia", "Nosferatu" }, { "Valeria", "Ghoul" }, { "Claudio", "Contadino normale" }, { "Vanessa", "Lupo del branco" }
                }
            ),
            new Partita(new String[][] { { "Luca", "Capo branco" }, { "Lucia", "Oste" } })
        };
        setPartitaNosferatu(partite[0]);
        setEsempioPartitaConGhoul(partite[1]);
        return Stream.of(Arguments.of(partite[0], VITTORIA), Arguments.of(partite[1], VITTORIA), Arguments.of(partite[2], SCONFITTA));
    }

    private static void setPartitaNosferatu(Partita partita)
    {
        String[] nomiVittime = { "Natasha", "Wanda" };
        String nomeLupo = "Steve";
        for(String nome : nomiVittime) nosferatizzazione(partita, "Capo branco", nome);
        rogo(partita, nomeLupo);
    }

    private static void setEsempioPartitaConGhoul(Partita partita)
    {
        String tipoLupo = "Lupo del branco", nomeVittima = "Claudio", nomeLupo = "Vanessa";
        nosferatizzazione(partita, tipoLupo, nomeVittima);
        rogo(partita, nomeLupo);
    }

    private static void rogo(Partita partita, String nomeLupo)
    {
        int numeroVoti = partita.getNumeroGiocatoriVivi() - 1;
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaBallottaggio();
        partita.terminaNotte();
    }

    private static void nosferatizzazione(Partita partita, String tipoLupo, String nome)
    {
        partita.attaccoLupi(tipoLupo, nome);
        partita.progenizzazioneNosferatu(nome);
        partita.terminaNotte();
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}