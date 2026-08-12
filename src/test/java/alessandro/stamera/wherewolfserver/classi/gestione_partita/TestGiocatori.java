package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatori
{

    private Giocatori giocatori;

    @BeforeEach public void setUp() { giocatori = new Giocatori(); }

    @Test public void testInserimentoGiocatori()
    {
        aggiungiGiocatori(new String[][] { { "Antonio", "Capo branco" }, { "Stefano", "Prete" } });
        verificaNumeroGiocatori(2);
    }

    @Test public void testEliminazioneGiocatori()
    {
        String nome = "Francesca";
        aggiungiGiocatore(nome, "Nonna");
        giocatori.eliminaGiocatore(nome);
        verificaNumeroGiocatori(0);
    }

    @Test public void testVotazione()
    {
        int numeroVoti = 3;
        String nome = "Anna";
        aggiungiGiocatore(nome, "Cappuccetto rosso");
        giocatori.incrementaVoti(nome, numeroVoti);
        verificaNumeroIntero(giocatori.getNumeroVoti(nome), numeroVoti);
    }

    @Test public void testAngeloCustodePresente()
    {
        String nome = "Otello";
        aggiungiGiocatore(nome, "Angelo custode");
        verificaVero(giocatori.isAngeloCustodePresente());
        verificaStringa(giocatori.getNomeAngeloCustode(), nome);
    }

    @ParameterizedTest @CsvSource({ "Assassino", "Capo gilda", "Guardia corrotta", "Ladra", "Spia" })
    public void testCriminale(String nomeCriminale)
    {
        String nome = "Sofia";
        aggiungiGiocatore(nome, nomeCriminale);
        verificaVero(giocatori.isCriminale(nome));
    }

    @Test public void testOratorePresente()
    {
        String[][] giocatori = new String[][] { { "Marco", "Oratore" }, { "Gianna", "Guaritore" } };
        aggiungiGiocatori(giocatori);
        verificaVero(isOratorePresente());
        verificaVero(isOratore(giocatori[0][0]));
        verificaFalso(isOratore(giocatori[1][0]));
    }

    @Test public void testOratoreAssente() { verificaFalso(isOratorePresente()); }

    @Test public void testNumeroRuoliCitta()
    {
        aggiungiGiocatori(new String[][] { { "Noemi", "Azzeccagarbugli" }, { "Elisa", "Inquisitore" }, { "Giuseppe", "Mercante" } });
        verificaNumeroIntero(giocatori.getNumeroRuoliCitta(), 2);
    }

    @Test public void testContadinoMostroPresente()
    {
        String nome = "Gianluigi";
        aggiungiGiocatore(nome, "Contadino mostro");
        verificaVero(isContadinoMostroPresente());
        verificaStringa(giocatori.getNomeContadinoMostro(), nome);
    }

    @Test public void testContadinoMostroAssente() { verificaFalso(isContadinoMostroPresente()); }

    @Test public void testIsContadinoMostro()
    {
        String[][] giocatori = new String[][] { { "Peter", "Contadino mostro" }, { "Gwen", "Contadino normale" } };
        aggiungiGiocatori(giocatori);
        verificaVero(isContadinoMostro(giocatori[0][0]));
        verificaFalso(isContadinoMostro(giocatori[1][0]));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin",
            "Guardia", "Guardia corrotta,", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario",
            "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Sensitiva", "Templare"
        }
    )
    public void testRomeo(String nomeRuolo)
    {
        String nome = "Marco";
        aggiungiGiocatori(new String[][] { { "Alessandro", "Giulietta" }, { nome, nomeRuolo } });
        giocatori.romeizzazione(nome);
        verificaVero(isRomeo(nome));
    }

    @Test public void testNosferatuPresente()
    {
        String nome = "Elena";
        aggiungiGiocatore(nome, "Nosferatu");
        verificaVero(isNosferatuPresente());
        verificaStringa(giocatori.getNomeNosferatu(), nome);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin",
            "Guardia", "Guardia corrotta,", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario",
            "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete",
            "Sidhe", "Spia", "Sensitiva", "Templare"
        }
    )
    public void testNosferatuNonPresente(String nomeRuolo)
    {
        String nome = "Mario";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(isNosferatuPresente());
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Ghoul", "Giovane lupo", "Giulietta", "Giullare", "Guardia",
            "Guardia corrotta", "Guaritore", "Inquisitore", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Mercante",
            "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Spia", "Sensitiva",
            "Templare", "Vampiro"
        }
    )
    public void testNonMegera(String nomeRuolo)
    {
        String nome = "Alessia";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(giocatori.isMegera(nome));
    }

    @Test public void testMegera()
    {
        String nome = "Orietta";
        aggiungiGiocatore(nome, "Megera");
        verificaVero(giocatori.isMegera(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Ghoul",
            "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno",
            "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna",
            "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare",
            "Vampiro"
        }
    )
    public void testNomeRuolo(String nomeRuolo)
    {
        String nome = "Elena";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaStringa(giocatori.getNomeRuolo(nome), nomeRuolo);
    }

    private boolean isRomeo(String nome) { return giocatori.isRomeo(nome); }

    private boolean isNosferatuPresente() { return giocatori.isNosferatuPresente(); }

    private void aggiungiGiocatori(String[][] giocatori)
    {
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private boolean isContadinoMostro(String nome) { return giocatori.isContadinoMostro(nome); }

    private boolean isContadinoMostroPresente() { return giocatori.isContadinoMostroPresente(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isOratore(String nome) { return giocatori.isOratore(nome); }

    private boolean isOratorePresente() { return giocatori.isOratorePresente(); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, new Giocatore(FACTORY.getRuolo(nomeRuolo)));
    }

    private void verificaNumeroGiocatori(int numeroGiocatori)
    {
        verificaNumeroIntero(giocatori.getNumeroGiocatori(), numeroGiocatori);
    }

    private void verificaNumeroIntero(int valore, int risultato) { assertThat(valore).isEqualTo(risultato); }

}