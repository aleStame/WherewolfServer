package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneAttaccoAmato extends IllegalStateException
{

    private final String tipoLupo, nomeLupo, ruoloAmato, nomeAmato, nomeAngeloCustode;

    public EccezioneAttaccoAmato(String tipoLupo, String nomeLupo, String ruoloAmato, String nomeAmato, String nomeAngeloCustode)
    {
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
        this.ruoloAmato = ruoloAmato;
        this.nomeAmato = nomeAmato;
        this.nomeAngeloCustode = nomeAngeloCustode;
    }

    @Override public String getMessage()
    {
        return
            "Il " + tipoLupo + " (" + nomeLupo + ") non può attaccare il " + ruoloAmato + " amato (" + nomeAmato + ").\nAvvisa l'Angelo custode "
            + "(" + nomeAngeloCustode + ") della sua morte.";
    }

}