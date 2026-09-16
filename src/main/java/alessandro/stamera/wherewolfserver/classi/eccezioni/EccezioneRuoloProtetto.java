package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public final class EccezioneRuoloProtetto extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima;

    private final Optional<String> nomeCapoBranco;

    public EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima)
    {
        this(ruoloVittima, nomeVittima, empty());
    }

    public EccezioneRuoloProtetto(String nomeCapoBranco, String ruoloVittima, String nomeVittima)
    {
        this(ruoloVittima, nomeVittima, of(nomeCapoBranco));
    }

    private EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima, Optional<String> nomeCapoBranco)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeCapoBranco = nomeCapoBranco;
    }

    @Override public String getMessage()
    {
        String risultato = getMessaggioRuoloProtetto();
        if(nomeCapoBranco.isPresent()) risultato = getMessaggioLupo();
        return risultato;
    }

    private String getMessaggioLupo()
    {
        return "Avvisa il Capo branco (" + nomeCapoBranco.get() + ") che non può attaccare il " + ruoloVittima + " (" + nomeVittima + ").";
    }

    private String getMessaggioRuoloProtetto()
    {
        String risultato = "";
        switch(ruoloVittima)
        {
            case "Ladra" -> risultato = getMessaggioLadra();
            case "Eremita" -> risultato = getMessaggioEremita();
        }
        return risultato;
    }

    private String getMessaggioEremita() { return nomeVittima + " è l'Eremita.\nAvvisa i lupi che l'attacco fallisce"; }

    private String getMessaggioLadra()
    {
        return
            "Impossible uccidere la Ladra (" + nomeVittima + ") perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi "
            + "dell'attacco fallito.";
    }

}
