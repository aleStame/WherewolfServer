package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneNegromanteControRomeo extends IllegalStateException
{

    public EccezioneNegromanteControRomeo(String nomeRomeo)
    {
        super(nomeRomeo + " non può essere maledetto perché Romeo.\nDì al Negromante di cambiare persona.");
    }

}