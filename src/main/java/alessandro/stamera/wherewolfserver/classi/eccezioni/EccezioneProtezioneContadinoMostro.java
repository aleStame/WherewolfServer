package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneProtezioneContadinoMostro extends IllegalArgumentException
{

    public EccezioneProtezioneContadinoMostro(String nomeStrega, String nomeContadinoMostro)
    {
        super
        (
            "La Strega (" + nomeStrega + ") ha cercato di proteggere il Contadino Mostro (" + nomeContadinoMostro +
            ").\nAvvisa Agatha della sua morte."
        );
    }

}