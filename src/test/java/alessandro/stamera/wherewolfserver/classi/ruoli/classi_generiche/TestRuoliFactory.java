package alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche;

import alessandro.stamera.wherewolfserver.classi.ruoli.*;
import alessandro.stamera.wherewolfserver.classi.ruoli.contadini.ContadinoEroe;
import alessandro.stamera.wherewolfserver.classi.ruoli.contadini.ContadinoLupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.contadini.ContadinoMostro;
import alessandro.stamera.wherewolfserver.classi.ruoli.contadini.ContadinoNormale;
import alessandro.stamera.wherewolfserver.classi.ruoli.guardie.AltraGuardia;
import alessandro.stamera.wherewolfserver.classi.ruoli.guardie.GuardiaCorrotta;
import alessandro.stamera.wherewolfserver.classi.ruoli.guardie.GuardiaPrincipale;
import alessandro.stamera.wherewolfserver.classi.ruoli.lupi.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestRuoliFactory
{

    @ParameterizedTest
    @CsvSource({ "Capo branco", "Lupo del branco", "Giovane lupo", "Lupo reietto", "Lupo solitario, Contadino discendente dei lupi" })
    public void testLupi(String nome) { verificaPresenza(FACTORY.getLupi(), getRuolo(nome)); }

    @ParameterizedTest @CsvSource({ "Guaritore, Mago, Megera, Negromante" })
    public void testMistici(String nome) { verificaPresenza(FACTORY.getMistici(), getRuolo(nome)); }

    @ParameterizedTest @CsvSource
    (
        { "Capo branco, Contadino discendente dei lupi, Giovane lupo, Lupo del branco, Lupo reietto, Lupo solitario, Negromante, Posseduto" }
    )
    public void testCreatureOmbra(String nome) { verificaPresenza(FACTORY.getCreatureOmbra(), getRuolo(nome)); }

    @ParameterizedTest @CsvSource
    (
        {
            "Altra guardia", "Angelo custode", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro",
            "Bracconiere", "Cacciatore", "Cacciatore di vampiri", "Capo branco", "Capo gilda", "Cappuccetto rosso", "Contadino eroe",
            "Contadino discendente dei lupi", "Contadino mostro", "Contadino normale", "Eremita", "Ghoul", "Giovane lupo", "Giulietta", "Giullare",
            "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno", "Lupo del branco", "Lupo reietto",
            "Lupo solitario", "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo",
            "Peccatore", "Posseduto", "Prete", "Sidhe", "Spia", "Sensitiva", "Templare"
        }
    )
    public void testNessunaSegnalazione(String nome)
    {
        FACTORY.annullaSegnalazioni();
        Ruolo ruolo = FACTORY.getRuolo(nome);
        assertThat(ruolo.isRomeo()).isFalse();
        assertThat(ruolo.isAmato()).isFalse();
        assertThat(ruolo.isInquisito()).isFalse();
        assertThat(ruolo.isSegnalatoAzzeccagarbugli()).isFalse();
    }

    private void verificaPresenza(Ruolo[] ruoli, Ruolo ruolo) { assertThat(ruoli).contains(ruolo); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

}