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
        return nomeGiocatore + " è " + getFraseProtettore() + ".\nAvvisa il Nosferatu (" + nomeNosferatu + ") della mancata progenizzazione.";
    }

    private String getFraseProtettore()
    {
        String risultato = "";
        switch(ruoloProtettore)
        {
            case "Strega" -> risultato = "protetto dalla Strega";
            case "Giulietta" -> risultato = "Romeo";
        }
        return risultato;
    }

}