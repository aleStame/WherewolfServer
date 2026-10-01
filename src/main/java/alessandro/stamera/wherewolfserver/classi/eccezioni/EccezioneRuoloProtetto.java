package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public final class EccezioneRuoloProtetto extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima;

    private final Optional<String> nomeLupo, tipoLupo;

    public EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima)
    {
        this(ruoloVittima, nomeVittima, empty(), empty());
    }

    public EccezioneRuoloProtetto(String tipoLupo, String nomeLupo, String ruoloVittima, String nomeVittima)
    {
        this(ruoloVittima, nomeVittima, of(tipoLupo), of(nomeLupo));
    }

    private EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima, Optional<String> tipoLupo, Optional<String> nomeLupo)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
    }

    @Override public String getMessage()
    {
        String risultato = "";
        switch(ruoloVittima)
        {
            case "Ladra" -> risultato = getMessaggioLadra();
            case "Eremita" -> risultato = getMessaggioEremita();
            case "Capo branco", "Lupo del branco", "Lupo reietto", "Lupo solitario" -> risultato = getMessaggioLupo();
        }
        return risultato;
    }

    private String getMessaggioLupo()
    {
        return "Avvisa il " + tipoLupo.get() + " (" + nomeLupo.get() + ") che non può attaccare il " + ruoloVittima + " (" + nomeVittima + ").";
    }

    private String getMessaggioEremita()
    {
        return "Avvisa il " + tipoLupo.get() + " (" + nomeLupo.get() + ") che non può attaccare " + nomeVittima + " (Eremita).";
    }

    private String getMessaggioLadra()
    {
        return
            "Impossible uccidere la Ladra (" + nomeVittima + ") perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi "
            + "dell'attacco fallito.";
    }

}
