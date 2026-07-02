package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneAttaccoGiocatoreProtetto extends IllegalStateException
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
        return nomeVittima + " non muore perché " + getCausa() + ".\nAvvisa i lupi della sua mancata morte.";
    }

    private String getCausa()
    {
        String causa = "protetto dalla Strega";
        if(isRomeo) causa = "Romeo";
        return causa;
    }

}
