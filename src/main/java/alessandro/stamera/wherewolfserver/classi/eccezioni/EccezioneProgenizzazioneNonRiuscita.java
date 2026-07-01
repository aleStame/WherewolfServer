package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneProgenizzazioneNonRiuscita extends IllegalStateException
{

    public EccezioneProgenizzazioneNonRiuscita
    (
        String nomeCacciatoreDiVampiri, String nomeAngeloCustode, String nomeVampiroAmato
    )
    {
        super
        (
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (" + nomeCacciatoreDiVampiri + ") causa la morte dell'Angelo custode (" +
            nomeAngeloCustode + ") del Vampiro amato (" + nomeVampiroAmato + ").\nAvvisa " + nomeAngeloCustode + " della sua morte."
        );
    }

    public EccezioneProgenizzazioneNonRiuscita(String nomeCacciatoreDiVampiri, String nomeGhoul)
    {
        super
        (
            "Il tentativo di vampirizzazione del Cacciatore di vampiri (" + nomeCacciatoreDiVampiri + ") causa la morte del Ghoul (" + nomeGhoul +
            ").\nAvvisa " + nomeGhoul + " della sua morte"
        );
    }

}