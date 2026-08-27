package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public final class EccezioneGildata extends IllegalArgumentException
{

    private final String nomeVittima;

    private final Optional<String> nomeCapoGilda;

    public EccezioneGildata(String nomeVittima) { this(nomeVittima, empty()); }

    public EccezioneGildata(String nomeVittima, String nomeCapoGilda) { this(nomeVittima, of(nomeCapoGilda)); }

    private EccezioneGildata(String nomeVittima, Optional<String> nomeCapoGilda)
    {
        this.nomeVittima = nomeVittima;
        this.nomeCapoGilda = nomeCapoGilda;
    }

    @Override public String getMessage()
    {
        String risultato = "Impossibile criminalizzare " + nomeVittima + ".";
        if(nomeCapoGilda.isPresent()) risultato += "\nIl Capo gilda (" + nomeCapoGilda.get() + ") muore.";
        return risultato;
    }

}
