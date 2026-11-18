package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneAttaccoPossedutoNonRiuscito extends IllegalStateException
{

    private final String nomeRuoloProtettore, nomeVittima, nomeRuoloVittima, nomePosseduto;

    public EccezioneAttaccoPossedutoNonRiuscito
    (
        String nomeRuoloProtettore, String nomeVittima, String nomeRuoloVittima, String nomePosseduto
    )
    {
        this.nomeRuoloProtettore = nomeRuoloProtettore;
        this.nomeVittima = nomeVittima;
        this.nomeRuoloVittima = nomeRuoloVittima;
        this.nomePosseduto = nomePosseduto;
    }

    @Override public String getMessage()
    {
        return
            nomeVittima + " (" + nomeRuoloVittima + ") è " + getFraseProtettore() + ".\nAvvisa il Posseduto (" + nomePosseduto +
            ") che non può possederlo.";
    }

    private String getFraseProtettore()
    {
        String risultato = "";
        switch(nomeRuoloProtettore)
        {
            case "Strega" -> risultato = "protetto dalla Strega";
            case "Giulietta" -> risultato = "Romeo";
        }
        return risultato;
    }

}