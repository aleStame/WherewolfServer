package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneMorteProgenizzatore extends IllegalArgumentException
{

    private final String ruoloProgenizzatore, tipoLupo, nomeLupo, nomeProgenizzatore;

    public EccezioneMorteProgenizzatore
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
        return
            "Impossibile " + getVerboCorretto() + " il " + tipoLupo + " (" + nomeLupo + ").\nAvvisa il " + ruoloProgenizzatore + " (" +
            nomeProgenizzatore + ") della sua morte.";
    }

    private String getVerboCorretto()
    {
        String verbo = "vampirizzare";
        if(ruoloProgenizzatore.equals("Nosferatu")) verbo = "progenizzare";
        return verbo;
    }

}