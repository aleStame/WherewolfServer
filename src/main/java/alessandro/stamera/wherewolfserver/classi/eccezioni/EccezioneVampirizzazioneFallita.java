package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class EccezioneVampirizzazioneFallita extends IllegalStateException
{

    private final String nomeVittima, nomeVampiro;

    private final Optional<String> ruoloProtettore;

    public EccezioneVampirizzazioneFallita(String nomeVittima, String nomeVampiro)
    {
        this(nomeVittima, nomeVampiro, empty());
    }

    public EccezioneVampirizzazioneFallita(String nomeVittima, String nomeVampiro, String ruoloProtettore)
    {
        this(nomeVittima, nomeVampiro, of(ruoloProtettore));
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
            "Impossibile vampirizzare " + nomeVittima + " " + getFraseMotivazione() + ".\nAvvisa il Vampiro (" + nomeVampiro +
            ") della mancata vampirizzazione.";
    }

    private String getFraseMotivazione()
    {
        String risultato = "perché ";
        if(ruoloProtettore.isPresent()) risultato += getFraseProtettore(ruoloProtettore.get());
        else risultato += "protetto dall'attacco del Vampiro";
        return risultato;
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