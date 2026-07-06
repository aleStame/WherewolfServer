package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneMorteProgenizzatore
{

    @ParameterizedTest @CsvSource
    (
        {
            "'Capo branco', 'Impossibile vampirizzare il Capo branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo del branco', 'Impossibile vampirizzare il Lupo del branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo reietto', 'Impossibile vampirizzare il Lupo reietto (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo solitario', 'Impossibile vampirizzare il Lupo solitario (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Contadino discendente dei lupi', 'Impossibile vampirizzare il Contadino discendente dei lupi (Giuliano).\nAvvisa il Vampiro " +
            "(Michele) della sua morte.'"
        }
    )
    public void testVampirizzazioneLupo(String tipoLupo, String messaggio)
    {
        assertThat(new EccezioneMorteProgenizzatore("Vampiro", tipoLupo, "Giuliano", "Michele").getMessage()).isEqualTo(messaggio);
    }

}