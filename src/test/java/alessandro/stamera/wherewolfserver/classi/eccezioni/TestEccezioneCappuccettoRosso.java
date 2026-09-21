package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneCappuccettoRosso
{

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Capo branco (Andrea).'",
            "Lupo del branco, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Lupo del branco " +
            "(Andrea).'",
            "Lupo reietto, 'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Lupo reietto (Andrea).'",
            "Lupo solitario, 'Andrea è il Lupo solitario. Cappuccetto rosso (Elena) si sveglia e lo riconosce'"
        }
    )
    public void testMessaggio(String tipoLupo, String messaggio)
    {
        assertThat(new EccezioneCappuccettoRosso(tipoLupo, "Andrea", "Elena").getMessage()).isEqualTo(messaggio);
    }

    @ParameterizedTest @CsvSource
    (
        {
            "Capo branco," +
            "'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Capo branco (Andrea).\nInoltre, essendo" +
            "il Capo branco Amato, muore Mario (Angelo custode).'",
            "Lupo del branco," +
            "'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Lupo del branco (Andrea).\nInoltre, " +
            "essendo il Lupo del branco Amato, muore Mario (Angelo custode).'",
            "Lupo reietto," +
            "'Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (Elena) riconosce il Lupo reietto (Andrea).\nInoltre, " +
            "essendo il Lupo reietto Amato, muore Mario (Angelo custode).'",
            "Lupo solitario," +
            "'Andrea è il Lupo solitario. Cappuccetto rosso (Elena) si sveglia e lo riconosce.\nInoltre, essendo il Lupo solitario Amato, muore " +
            "Mario (Angelo custode).'"
        }
    )
    public void testMessaggioLupoAmato(String tipoLupo, String messaggio)
    {
        assertThat(new EccezioneCappuccettoRosso(tipoLupo, "Andrea", "Elena", "Mario").getMessage()).isEqualTo(messaggio);
    }

}