package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneVampirizzazioneLupi extends IllegalArgumentException
{

    public EccezioneVampirizzazioneLupi(String tipoLupo, String nomeLupo, String nomeVampiro)
    {
      super("Impossibile vampirizzare il " + tipoLupo + " (" + nomeLupo + ").\nAvvisa il Vampiro (" + nomeVampiro + ") della sua morte.");
    }

}