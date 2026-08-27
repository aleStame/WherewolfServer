package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriEliminati
{

    private GiocatoriEliminati giocatori;

    @BeforeEach public void setUp() { giocatori = new GiocatoriEliminati(); }

    @Test public void testBardoPresente()
    {
        aggiungiGiocatore("Pino", "Bardo");
        verificaVero(isBardoPresente());
    }

    @Test public void testBardoAssente() { assertThat(isBardoPresente()).isFalse(); }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia, BIANCA", "Angelo custode, BIANCA", "Assassino, NERA", "Azzeccagarbugli, BIANCA", "Bardo, BIANCA", "Becchino, BIANCA",
            "Bocca di rosa, NERA", "Boia, NERA", "Borgomastro, BIANCA", "Bracconiere, BIANCA", "Cacciatore, BIANCA", "Cacciatore di vampiri, BIANCA",
            "Capo branco, NERA", "Capo gilda, BIANCA", "Cappuccetto rosso, BIANCA", "Contadino eroe, BIANCA",
            "Contadino discendente dei lupi, BIANCA", "Contadino mostro, BIANCA", "Contadino normale, BIANCA", "Eremita, BIANCA", "Ghoul, BIANCA",
            "Giovane lupo, NERA", "Giulietta, BIANCA", "Giullare, BIANCA", "Goblin, NERA", "Guardia, BIANCA", "Guardia corrotta, NERA",
            "Guaritore, BIANCA", "Inquisitore, BIANCA", "Ladra, BIANCA", "Leprecauno, BIANCA", "Lupo del branco, NERA", "Lupo reietto, NERA",
            "Lupo solitario, NERA", "Mago, BIANCA", "Medium, BIANCA", "Megera, NERA", "Mercante, BIANCA", "Monaco, BIANCA", "Negromante, NERA",
            "Nonna, BIANCA", "Nosferatu, NERA", "Oratore, BIANCA", "Oste, BIANCA", "Pazzo, BIANCA", "Peccatore, NERA", "Posseduto, NERA",
            "Prete, BIANCA", "Sidhe, BIANCA", "Spia, BIANCA", "Sensitiva, BIANCA", "Templare, BIANCA", "Vampiro, NERA"
        }
    )
    public void testControlloMedium(String nomeRuolo, Aura aura)
    {
        String nome = "Paolo";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(giocatori.controlloMedium(nome)).isEqualTo(aura);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino discendente dei lupi", "Contadino eroe",
            "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giullare", "Goblin", "Guardia", "Guardia corrotta",
            "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto", "Lupo solitario", "Mago", "Medium", "Megera",
            "Mercante", "Monaco", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore", "Posseduto", "Prete", "Sensitiva", "Sidhe", "Spia",
            "Strega", "Templare", "Vampiro"
        }
    )
    public void testNoNegromante(String nomeRuolo)
    {
        String nome = "Salvo";
        aggiungiGiocatore(nome, nomeRuolo);
        assertThat(isNegromante(nome)).isFalse();
    }

    @Test public void testNegromante()
    {
        String nome = "Salvo";
        aggiungiGiocatore(nome, "Negromante");
        verificaVero(isNegromante(nome));
    }

    private boolean isNegromante(String nome) { return giocatori.isNegromante(nome); }

    private boolean isBardoPresente() { return giocatori.isBardoPresente(); }

    private void aggiungiGiocatore(String nomeGiocatore, String nomeRuolo)
    {
        giocatori.aggiungiGiocatore(nomeGiocatore, new Giocatore(FACTORY.getRuolo(nomeRuolo)));
    }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

}