package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;

public final class EccezioneGildata extends IllegalArgumentException
{

    private final String nomeVittima;

    private final Optional<String> nomeCapoGilda;

    public EccezioneGildata(String nomeVittima)
    {
        this.nomeVittima = nomeVittima;
        nomeCapoGilda = Optional.empty();
    }

    public EccezioneGildata(String nomeVittima, String nomeCapoGilda)
    {
        this.nomeVittima = nomeVittima;
        this.nomeCapoGilda = Optional.of(nomeCapoGilda);
    }

    @Override public String getMessage()
    {
        String risultato = "Impossibile criminalizzare " + nomeVittima + ".";
        if(nomeCapoGilda.isPresent()) risultato += "\nIl Capo gilda (" + nomeCapoGilda.get() + ") muore.";
        return risultato;
    }

}
