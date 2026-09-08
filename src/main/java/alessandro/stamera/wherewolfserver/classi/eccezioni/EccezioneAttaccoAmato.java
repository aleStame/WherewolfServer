package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneAttaccoAmato extends IllegalStateException
{

    private final String tipoLupo, nomeLupo, ruoloAmato, nomeAmato, nomeAngeloCustode;

    public EccezioneAttaccoAmato(String nomeLupo, String nomeAngeloCustode)
    {
        this("Capo branco", nomeLupo, "Capo branco", nomeLupo, nomeAngeloCustode);
    }

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
        if(tipoLupo.equals(ruoloAmato))
            return
                "Il Capo branco amato (" + nomeAmato + ") non può suicidarsi.\nAvvisa l'Angelo custode (" + nomeAngeloCustode +
                ") della sua morte.";
        else
            return
                "Il " + tipoLupo + " (" + nomeLupo + ") non può attaccare il " + ruoloAmato + " amato (" + nomeAmato + ").\nAvvisa l'Angelo " +
                "custode (" + nomeAngeloCustode + ") della sua morte.";
    }

}