package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneAttaccoGiocatoreProtetto extends IllegalStateException
{

    private final boolean isRomeo;

    private final String nomeVittima;

    public EccezioneAttaccoGiocatoreProtetto(boolean isRomeo, String nomeVittima)
    {
        this.isRomeo = isRomeo;
        this.nomeVittima = nomeVittima;
    }

    @Override public String getMessage()
    {
        String causa = "protetto dalla Strega";
        if(isRomeo) causa = "Romeo";
        return nomeVittima + " non muore perché " + causa + ".\nAvvisa i lupi della sua mancata morte.";
    }

}
