package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneRuoloProtetto extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima;

    public EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
    }

    @Override public String getMessage()
    {
        String risultato = "";
        switch(ruoloVittima)
        {
            case "Ladra" -> risultato =
                "Impossible uccidere la Ladra (" + nomeVittima + ") perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi " +
                "dell'attacco fallito.";
            case "Eremita" -> risultato = nomeVittima + " è l'Eremita.\nAvvisa i lupi che l'attacco fallisce";
        }
        return risultato;
    }

}
