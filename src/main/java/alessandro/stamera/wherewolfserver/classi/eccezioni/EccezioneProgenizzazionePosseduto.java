package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneProgenizzazionePosseduto extends RuntimeException
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
        String verbo = "";
        switch(ruoloProgenizzatore)
        {
            case "Nosferatu" -> verbo = "progenizzare";
            case "Vampiro" -> verbo = "vampirizzare";
        }
        return
            "Il " + ruoloProgenizzatore + " (" + nomeProgenizzatore + ") non può " + verbo + " il Posseduto (" + nomePosseduto + ").\n" +
            nomeProgenizzatore + " diventerà il Posseduto e " + nomePosseduto + " che morirà.";
    }

}