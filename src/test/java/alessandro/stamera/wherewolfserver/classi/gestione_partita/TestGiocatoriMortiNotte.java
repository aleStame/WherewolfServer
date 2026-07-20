package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriMortiNotte
{

    private GiocatoriMortiNotte giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriMortiNotte(); }

    @ParameterizedTest @CsvSource({ "Prete, RIUSCITO", "Eremita, FALLITO" })
    public void testProgenieNosferatu(String nomeRuolo, EsitoAttacco esito)
    {
        String nome = "Marco";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.progenizzazioneNosferatu(nome)).isEqualTo(esito);
        giocatori.ripristina(nome);
    }

    @Test public void testLupo()
    {
        String[][] giocatori = new String[][] { { "Katia", "Nosferatu" }, { "Valeria", "Giovane lupo" } };
        aggiungiGiocatori(giocatori);
        verificaFalso(isLupo(giocatori[0][0]));
        verificaVero(isLupo(giocatori[1][0]));
    }

    @Test public void testPazzo()
    {
        String[][] giocatori = new String[][] { { "Pablo", "Templare" }, { "Pedro", "Pazzo" } };
        aggiungiGiocatori(giocatori);
        verificaFalso(isPazzo(giocatori[0][0]));
        verificaVero(isPazzo(giocatori[1][0]));
    }

    @ParameterizedTest @EnumSource(TipoContadino.class) public void testContadinoPresente(TipoContadino tipoContadino)
    {
        String nome = "Gianfranco";
        aggiungiGiocatore(nome, tipoContadino.toString());
        verificaVero(giocatori.isContadino(nome));
        assertThat(giocatori.getTipoContadino(nome)).isEqualTo(tipoContadino);
    }

    @Test public void testContadinoAssente() { verificaFalso(giocatori.isContadino("Giampiero")); }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Eremita", "Ghoul",
            "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno",
            "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna",
            "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare",
            "Vampiro"
        }
    )
    public void testRuoloNonContadino(String nomeRuolo)
    {
        String nome = "Giuseppina";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(giocatori.isContadino(nome));
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Eremita", "Ghoul",
            "Giovane lupo", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno",
            "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna",
            "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        }
    )
    public void testPossedutoAssente(String nomeRuolo)
    {
        String nome = "Gianangelo";
        aggiungiGiocatore(nome, nomeRuolo);
        verificaFalso(giocatori.isPossedutoPresente());
    }

    private void aggiungiGiocatori(String[][] giocatori)
    {
        for(String[] giocatore : giocatori) aggiungiGiocatore(giocatore[0], giocatore[1]);
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

    private boolean isPazzo(String nome) { return this.giocatori.isPazzo(nome); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, FACTORY.getRuolo(nomeRuolo));
    }

    private boolean isLupo(String nome) { return giocatori.isLupo(nome); }

}