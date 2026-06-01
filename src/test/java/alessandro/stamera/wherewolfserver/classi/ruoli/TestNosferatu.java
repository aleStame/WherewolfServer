package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.VITTORIA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.NOSFERATU;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;
import static org.assertj.core.api.Assertions.assertThat;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;

public final class TestNosferatu
{

    private static final String NOME = "Nosferatu";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(NOSFERATU); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @Test public void testAura() { verificaAuraNera(ruolo.getAura()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(3); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce il Ghoul e individua la Megera. Dalla seconda notte, individua i giocatori uccisi quella notte, può " +
            "indicarne uno e farlo tornare in vita. Se è un lupo mannaro o il Cacciatore di vampiri, il Nosferatu viene ucciso. Se è un " +
            "mistico, non accade nulla. Altrimenti, lo riconosce e diventa una progenie vampirica con aura oscura e fazione Nosferatu";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testCreatureOmbra() { verificaVero(ruolo.isTrattoPresente(CREATURA_OMBRA)); }

    @Test public void testNosferatu() { verificaVero(ruolo.isNosferatu()); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCitta()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testGhoul() { verificaFalso(ruolo.isGhoul()); }

    @Test public void testGiullare() { verificaFalso(ruolo.isGiullare()); }

    @Test public void testGoblin() { verificaFalso(ruolo.isGoblin()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testPazzo() { verificaFalso(ruolo.isPazzo()); }

    @Test public void testPeccatore() { verificaFalso(ruolo.isPeccatore()); }

    @Test public void testPiccoloPopolo() { verificaFalso(ruolo.isPiccoloPopolo()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testSegnalazioneBoia()
    {
        ruolo.segnalazioneBoia();
        verificaVero(isSegnalatoBoia());
        ruolo.annullaSegnalazioneBoia();
        verificaFalso(isSegnalatoBoia());
    }

    @Test public void testVittoriaNosferatu()
    {
        String[] nomiVittime = { "Natasha", "Wanda" };
        String nomeLupo = "Steve";
        String[][] giocatori =
            new String[][] { { "Tony", "Nosferatu" }, { nomeLupo, "Capo branco" }, { nomiVittime[0], "Prete" }, { nomiVittime[1], "Peccatore" } };
        assertThat(ruolo.getEsitoPartita(getEsempioPartita(giocatori, nomiVittime, nomeLupo))).isEqualTo(VITTORIA);
    }

    @Test public void testVittoriaNosferatuConGhoul()
    {
        String tipoLupo = "Capo branco", nomeVittima = "Claudio", nomeLupo = "Vanessa";
        String[][] giocatori =
            new String[][] { { "Katia", "Nosferatu" }, { "Valeria", "Ghoul" }, { nomeVittima, "Contadino normale" }, { nomeLupo, tipoLupo } };
        Partita partita = new Partita(giocatori);
        partita.attaccoLupi(tipoLupo, nomeVittima);
        partita.progenizzazioneNosferatu(nomeVittima);
        partita.terminaNotte();
        int numeroVoti = partita.getNumeroGiocatoriVivi() - 1;
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaBallottaggio();
        partita.terminaNotte();
        assertThat(ruolo.getEsitoPartita(partita)).isEqualTo(VITTORIA);
    }

    private Partita getEsempioPartita(String[][] giocatori, String[] nomiVittime, String nomeLupo)
    {
        Partita partita = new Partita(giocatori);
        for(String nome : nomiVittime)
        {
            partita.attaccoLupi(giocatori[2][1], nome);
            partita.progenizzazioneNosferatu(nome);
            partita.terminaNotte();
        }
        int numeroVoti = partita.getNumeroGiocatoriVivi() - 1;
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaVotazioni();
        partita.incrementaVoti(nomeLupo, numeroVoti);
        partita.terminaBallottaggio();
        partita.terminaNotte();
        return partita;
    }

    private boolean isSegnalatoBoia() {
        return ruolo.isSegnalatoBoia();
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}