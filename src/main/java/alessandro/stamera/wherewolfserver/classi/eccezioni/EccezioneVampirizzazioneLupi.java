package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneVampirizzazioneLupi extends IllegalArgumentException
{

    private final String ruoloProgenizzatore, tipoLupo, nomeLupo, nomeProgenizzatore;

    public EccezioneVampirizzazioneLupi
    (
        String ruoloProgenizzatore, String tipoLupo, String nomeLupo, String nomeProgenizzatore
    )
    {
        this.ruoloProgenizzatore = ruoloProgenizzatore;
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
        this.nomeProgenizzatore = nomeProgenizzatore;
    }

    @Override public String getMessage()
    {
        String verbo = "vampirizzare";
        if(ruoloProgenizzatore.equals("Nosferatu")) verbo = "progenizzare";
        return
            "Impossibile " + verbo + " il " + tipoLupo + " (" + nomeLupo + ").\nAvvisa il " + ruoloProgenizzatore + " (" + nomeProgenizzatore +
            ") della sua morte.";
    }

}