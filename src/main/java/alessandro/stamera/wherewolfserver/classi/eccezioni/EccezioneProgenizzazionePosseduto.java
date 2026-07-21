package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneProgenizzazionePosseduto extends IllegalArgumentException
{

    private final String ruoloProgenizzatore, nomeProgenizzatore, nomePosseduto;

    public EccezioneProgenizzazionePosseduto(String ruoloProgenizzatore, String nomeProgenizzatore, String nomePosseduto)
    {
        this.ruoloProgenizzatore = ruoloProgenizzatore;
        this.nomeProgenizzatore = nomeProgenizzatore;
        this.nomePosseduto = nomePosseduto;
    }

    @Override public String getMessage()
    {
        return
            "Il " + ruoloProgenizzatore + " (" + nomeProgenizzatore + ") non può " + getVerbo() + " il Posseduto (" + nomePosseduto + ").\n" +
            nomeProgenizzatore + " diventerà il Posseduto e " + nomePosseduto + " che morirà.";
    }

    private String getVerbo()
    {
        String verbo = "";
        switch(ruoloProgenizzatore)
        {
            case "Nosferatu" -> verbo = "progenizzare";
            case "Vampiro" -> verbo = "vampirizzare";
        }
        return verbo;
    }

}