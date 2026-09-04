package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneVampirizzazioneFallita extends IllegalStateException
{

    private final String nomeVittima, nomeVampiro, ruoloProtettore;

    public EccezioneVampirizzazioneFallita(String nomeVittima, String nomeVampiro, String ruoloProtettore)
    {
        this.nomeVittima = nomeVittima;
        this.nomeVampiro = nomeVampiro;
        this.ruoloProtettore = ruoloProtettore;
    }

    @Override public String getMessage()
    {
        return
            "Impossibile vampirizzare " + nomeVittima + " perché " + getFraseProtettore(ruoloProtettore) + ".\nAvvisa il Vampiro (" + nomeVampiro +
            ") della mancata vampirizzazione.";
    }

    private String getFraseProtettore(String protettore)
    {
        String risultato = null;
        switch(protettore)
        {
            case "Strega" -> risultato = "protetto dalla Strega";
            case "Giulietta" -> risultato = "Romeo";
        }
        return risultato;
    }

}