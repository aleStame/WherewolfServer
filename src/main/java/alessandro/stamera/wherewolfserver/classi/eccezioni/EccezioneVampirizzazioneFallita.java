package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;

public class EccezioneVampirizzazioneFallita extends IllegalStateException
{

    private final String nomeVittima, nomeVampiro;
    private final Optional<String> ruoloProtettore;

    public EccezioneVampirizzazioneFallita(String nomeVittima, String nomeVampiro)
    {
        this(nomeVittima, nomeVampiro, Optional.empty());
    }

    public EccezioneVampirizzazioneFallita(String nomeVittima, String nomeVampiro, String ruoloProtettore)
    {
        this(nomeVittima, nomeVampiro, Optional.of(ruoloProtettore));
    }

    private EccezioneVampirizzazioneFallita(String nomeVittima, String nomeVampiro, Optional<String> ruoloProtettore)
    {
        this.nomeVittima = nomeVittima;
        this.nomeVampiro = nomeVampiro;
        this.ruoloProtettore = ruoloProtettore;
    }

    @Override public String getMessage()
    {
        return
            "Impossibile vampirizzare " + nomeVittima + " perché .\nAvvisa il Vampiro (" + nomeVampiro +
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