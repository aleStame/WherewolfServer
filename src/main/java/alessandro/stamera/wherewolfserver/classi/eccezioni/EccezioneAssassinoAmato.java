package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneAssassinoAmato extends IllegalStateException
{

    private final String nomeAmato, nomeAssassino, nomeAngeloCustode;

    public EccezioneAssassinoAmato(String nomeAmato, String nomeAssassino, String nomeAngeloCustode)
    {
        this.nomeAmato = nomeAmato;
        this.nomeAssassino = nomeAssassino;
        this.nomeAngeloCustode = nomeAngeloCustode;
    }

    @Override public String getMessage()
    {
        return
            "L'attacco dell'amato (" + nomeAmato + ") da parte dell'Assassino (" + nomeAssassino + ") causa la morte del suo Angelo custode (" +
            nomeAngeloCustode + ").\nAvvisa " + nomeAngeloCustode + " dell'attacco subito.";
    }

}