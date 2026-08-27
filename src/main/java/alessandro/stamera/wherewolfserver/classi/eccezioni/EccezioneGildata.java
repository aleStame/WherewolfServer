package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneGildata extends RuntimeException
{

    public EccezioneGildata(String nomeVittima) {
        super("Impossibile criminalizzare " + nomeVittima + ".");
    }

    public EccezioneGildata(String nomeVittima, String nomeCapoGilda)
    {
        super("Impossibile criminalizzare " + nomeVittima + ".\nIl Capo gilda (" + nomeCapoGilda + ") muore.");
    }

}
