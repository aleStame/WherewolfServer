package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestEccezioneMorteProgenizzatore
{

    @ParameterizedTest @CsvSource
    (
        {
            "'Capo branco', Vampiro, 'Impossibile vampirizzare il Capo branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Capo branco', Nosferatu, 'Impossibile progenizzare il Capo branco (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo del branco', Vampiro, 'Impossibile vampirizzare il Lupo del branco (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo del branco', Nosferatu, 'Impossibile progenizzare il Lupo del branco (Giuliano).\nAvvisa il Nosferatu (Michele) della sua " +
            "morte.'",
            "'Lupo reietto', Vampiro, 'Impossibile vampirizzare il Lupo reietto (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo reietto', Nosferatu, 'Impossibile progenizzare il Lupo reietto (Giuliano).\nAvvisa il Nosferatu (Michele) della sua morte.'",
            "'Lupo solitario', Vampiro, 'Impossibile vampirizzare il Lupo solitario (Giuliano).\nAvvisa il Vampiro (Michele) della sua morte.'",
            "'Lupo solitario', Nosferatu, 'Impossibile progenizzare il Lupo solitario (Giuliano).\nAvvisa il Nosferatu (Michele) della sua " +
            "morte.'",
            "'Contadino discendente dei lupi', Vampiro, 'Impossibile vampirizzare il Contadino discendente dei lupi (Giuliano).\nAvvisa il " +
            "Vampiro (Michele) della sua morte.'",
            "'Contadino discendente dei lupi', Nosferatu, 'Impossibile progenizzare il Contadino discendente dei lupi (Giuliano).\nAvvisa il " +
            "Nosferatu (Michele) della sua morte.'"
        }
    )
    public void testMessaggioErrore(String tipoLupo, String progenizzatore, String messaggio)
    {
        EccezioneMorteProgenizzatore eccezione =
            new EccezioneMorteProgenizzatore(progenizzatore, tipoLupo, "Giuliano", "Michele");
        assertThat(eccezione.getMessage()).isEqualTo(messaggio);
    }

}