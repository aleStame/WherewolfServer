package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneAttaccoAmato
{

    @ParameterizedTest @MethodSource("getEsempioAttaccoAmato")
    public void testMessaggio(String tipoLupo, String nomeRuolo, String messaggio)
    {
        String risultato =
            new EccezioneAttaccoAmato(tipoLupo, "Maria", nomeRuolo, "Giuseppe", "Erode").getMessage();
        assertThat(risultato).isEqualTo(messaggio);
    }

    private static Stream<Arguments> getEsempioAttaccoAmato()
    {
        String[] tipiLupo = { "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" };
        String[] nomiRuoli =
        {
            "Altra guardia", "Assassino", "Azzeccagarbugli", "Bardo", "Becchino", "Bocca di rosa", "Boia", "Borgomastro", "Bracconiere",
            "Cacciatore", "Cacciatore di vampiri", "Capo gilda", "Cappuccetto rosso", "Contadino eroe", "Contadino mostro", "Contadino normale",
            "Eremita", "Ghoul", "Giulietta", "Giullare", "Goblin", "Guardia", "Guardia corrotta", "Guaritore", "Inquisitore", "Ladra", "Leprecauno",
            "Mago", "Medium", "Megera", "Mercante", "Monaco", "Negromante", "Nonna", "Nosferatu", "Oratore", "Oste", "Pazzo", "Peccatore",
            "Posseduto", "Prete", "Sidhe", "Spia", "Strega", "Sensitiva", "Templare", "Vampiro"
        };
        List<Arguments> argomenti = new ArrayList<>();
        for(String tipoLupo : tipiLupo) for(String nomeRuolo : nomiRuoli)
        {
            String messaggio =
                "Il " + tipoLupo + " (Maria) non può attaccare il " + nomeRuolo + " amato (Giuseppe).\n Avvisa l'Angelo custode (Erode) della " +
                "sua morte.";
            argomenti.add(Arguments.of(tipoLupo, nomeRuolo, messaggio));
        }
        return argomenti.stream();
    }

}