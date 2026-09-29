package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneProgenizzazioneGiocatoreProtetto extends IllegalStateException
{

    private final String nomeGiocatore, ruoloProtettore, nomeNosferatu;

    public EccezioneProgenizzazioneGiocatoreProtetto(String nomeGiocatore, String ruoloProtettore, String nomeNosferatu)
    {
        this.nomeGiocatore = nomeGiocatore;
        this.ruoloProtettore = ruoloProtettore;
        this.nomeNosferatu = nomeNosferatu;
    }

    @Override public String getMessage()
    {
        String risultato = nomeGiocatore + " è ";
        switch(ruoloProtettore)
        {
            case "Strega" -> risultato += "protetto dalla Strega.\n";
            case "Giulietta" -> risultato += "Romeo.\n";
        }
        risultato += ("Avvisa il Nosferatu (" + nomeNosferatu + ") della mancata progenizzazione.");
        return risultato;
    }

}