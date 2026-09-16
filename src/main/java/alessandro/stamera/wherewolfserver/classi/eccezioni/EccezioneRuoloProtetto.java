package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;

public final class EccezioneRuoloProtetto extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima;

    private final Optional<String> nomeCapoBranco;

    public EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        nomeCapoBranco = Optional.empty();
    }

    public EccezioneRuoloProtetto(String nomeCapoBranco, String ruoloVittima, String nomeVittima)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeCapoBranco = Optional.of(nomeCapoBranco);
    }

    @Override public String getMessage()
    {
        String risultato = "";
        if(nomeCapoBranco.isPresent())
            risultato = "Avvisa il Capo branco (" + nomeCapoBranco.get() + ") che non può attaccare il " + ruoloVittima + " (" + nomeVittima + ").";
        else
        {
            switch(ruoloVittima)
            {
                case "Ladra" -> risultato =
                    "Impossible uccidere la Ladra (" + nomeVittima + ") perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi "
                    + "dell'attacco fallito.";
                case "Eremita" -> risultato = nomeVittima + " è l'Eremita.\nAvvisa i lupi che l'attacco fallisce";
            }
        }
        return risultato;
    }

}
