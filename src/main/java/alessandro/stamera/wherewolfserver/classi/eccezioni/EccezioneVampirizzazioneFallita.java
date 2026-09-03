package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneVampirizzazioneFallita extends IllegalStateException
{

    public EccezioneVampirizzazioneFallita(String nomeRomeo, String nomeVampiro)
    {
        super("Impossibile vampirizzare " + nomeRomeo + " perché Romeo.\nAvvisa il Vampiro (" + nomeVampiro + ") della mancata vampirizzazione.");
    }

}