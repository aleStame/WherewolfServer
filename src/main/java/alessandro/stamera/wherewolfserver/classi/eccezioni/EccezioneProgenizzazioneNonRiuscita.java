package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneProgenizzazioneNonRiuscita extends IllegalArgumentException
{

    public EccezioneProgenizzazioneNonRiuscita
    (
        String ruoloVittima, String nomeVittima, String nomeAngeloCustode, String nomeVampiroAmato
    )
    {
        super
        (
            "Il tentativo di vampirizzazione del " + ruoloVittima + " (" + nomeVittima + ") causa la morte dell'Angelo custode (" + nomeAngeloCustode
            + ") del Vampiro amato (" + nomeVampiroAmato + ").\nAvvisa " + nomeAngeloCustode + " della sua morte."
        );
    }

    public EccezioneProgenizzazioneNonRiuscita(String ruoloVittima, String nomeVittima, String nomeGhoul)
    {
        super
        (
            "Il tentativo di vampirizzazione del " + ruoloVittima + " (" + nomeVittima + ") causa la morte del Ghoul (" + nomeGhoul + ").\nAvvisa " +
            nomeGhoul + " della sua morte."
        );
    }

}