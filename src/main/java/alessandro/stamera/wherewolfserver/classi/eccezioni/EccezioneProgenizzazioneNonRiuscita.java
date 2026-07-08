package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneProgenizzazioneNonRiuscita extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima, nomeProgenizzatore, ruoloProgenizzatore, nomeDifensore, ruoloDifensore;

    public EccezioneProgenizzazioneNonRiuscita
    (
        String ruoloVittima, String nomeVittima, String ruoloProgenizzatore, String nomeProgenizzatore, String ruoloDifensore, String nomeDifensore
    )
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeProgenizzatore = nomeProgenizzatore;
        this.ruoloProgenizzatore = ruoloProgenizzatore;
        this.nomeDifensore = nomeDifensore;
        this.ruoloDifensore = ruoloDifensore;
    }

    @Override public String getMessage()
    {
        String messaggio;
        if(ruoloDifensore.equals("Angelo custode")) messaggio = getMessaggioErroreAngeloCustode();
        else messaggio = getMessaggioErroreGhoul();
        return messaggio;
    }

    private String getMessaggioErroreAngeloCustode()
    {
        return
            "Il tentativo di " + getSostantivo() + " del " + ruoloVittima + " (" + nomeVittima + ") causa la morte dell'Angelo custode (" +
            nomeDifensore + ") del " + ruoloProgenizzatore + " amato (" + nomeProgenizzatore + ").\nAvvisa " + nomeDifensore + " della sua morte.";
    }

    private String getMessaggioErroreGhoul()
    {
        return
            "Il tentativo di " + getSostantivo() + " del " + ruoloVittima + " (" + nomeVittima + ") causa la morte del Ghoul (" + nomeDifensore +
            ").\nAvvisa " + nomeDifensore + " della sua morte.";
    }

    private String getSostantivo()
    {
        String sostantivo = "vampirizzazione";
        if(ruoloProgenizzatore.equals("Nosferatu")) sostantivo = "progenizzazione";
        return sostantivo;
    }

}