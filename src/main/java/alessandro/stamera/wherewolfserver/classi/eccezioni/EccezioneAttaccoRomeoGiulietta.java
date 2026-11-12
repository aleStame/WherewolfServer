package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneAttaccoRomeoGiulietta extends IllegalStateException
{

    public EccezioneAttaccoRomeoGiulietta(String nomeRomeo, String nomeGiulietta)
    {
        super(nomeRomeo + " è Romeo.\nAvvisa anche " + nomeGiulietta + " (Giulietta) del fatto che deve morire.");
    }

}