package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneAttaccoGiocatoreProtetto extends IllegalStateException
{

    private final String nomeVittima;

    public EccezioneAttaccoGiocatoreProtetto(boolean isRomeo, String nomeVittima) { this.nomeVittima = nomeVittima; }

    @Override public String getMessage()
    {
        return nomeVittima + " non muore perché protetto dalla Strega.\nAvvisa i lupi della sua mancata morte.";
    }

}
