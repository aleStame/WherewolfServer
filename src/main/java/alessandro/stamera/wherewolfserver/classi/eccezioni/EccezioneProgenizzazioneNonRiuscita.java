package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;

public final class EccezioneProgenizzazioneNonRiuscita extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima;

    private final Optional<String> nomeAngeloCustode, nomeVampiroAmato, nomeGhoul;

    public EccezioneProgenizzazioneNonRiuscita
    (
        String ruoloVittima, String nomeVittima, String nomeAngeloCustode, String nomeVampiroAmato
    )
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeAngeloCustode = inizializzaStringa(nomeAngeloCustode);
        this.nomeVampiroAmato = inizializzaStringa(nomeVampiroAmato);
        this.nomeGhoul = Optional.empty();
    }

    public EccezioneProgenizzazioneNonRiuscita(String ruoloVittima, String nomeVittima, String nomeGhoul)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeAngeloCustode = Optional.empty();
        this.nomeVampiroAmato = Optional.empty();
        this.nomeGhoul = inizializzaStringa(nomeGhoul);
    }

    @Override public String getMessage()
    {
        String messaggio;
        if(nomeAngeloCustode.isPresent() && nomeVampiroAmato.isPresent())
        {
            String nomeAngeloCustode = this.nomeAngeloCustode.get();
            messaggio =
                "Il tentativo di vampirizzazione del " + ruoloVittima + " (" + nomeVittima + ") causa la morte dell'Angelo custode (" +
                nomeAngeloCustode + ") del Vampiro amato (" + nomeVampiroAmato.get() + ").\nAvvisa " + nomeAngeloCustode + " della sua morte.";
        }
        else
        {
            String nomeGhoul = this.nomeGhoul.get();
            messaggio =
                "Il tentativo di vampirizzazione del " + ruoloVittima + " (" + nomeVittima + ") causa la morte del Ghoul (" + nomeGhoul +
                ").\nAvvisa " + nomeGhoul + " della sua morte.";
        }
        return messaggio;
    }

    private Optional<String> inizializzaStringa(String stringa) { return Optional.of(stringa); }

}