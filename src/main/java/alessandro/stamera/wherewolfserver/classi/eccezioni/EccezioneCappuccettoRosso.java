package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class EccezioneCappuccettoRosso extends IllegalStateException
{

    private final String tipoLupo, nomeLupo, nomeCappuccettoRosso;

    private final Optional<String> nomeAngeloCustode;

    public EccezioneCappuccettoRosso(String tipoLupo, String nomeLupo, String nomeCappuccettoRosso)
    {
        this(tipoLupo, nomeLupo, nomeCappuccettoRosso, empty());
    }

    public EccezioneCappuccettoRosso(String tipoLupo, String nomeLupo, String nomeCappuccettoRosso, String nomeAngeloCustode)
    {
        this(tipoLupo, nomeLupo, nomeCappuccettoRosso, of(nomeAngeloCustode));
    }

    private EccezioneCappuccettoRosso
    (
        String tipoLupo, String nomeLupo, String nomeCappuccettoRosso, Optional<String> nomeAngeloCustode
    )
    {
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
        this.nomeCappuccettoRosso = nomeCappuccettoRosso;
        this.nomeAngeloCustode = nomeAngeloCustode;
    }

    @Override public String getMessage()
    {
        String messaggio = getMessaggioPrincipale();
        if(isAngeloCustodePresente()) messaggio += ("\n" + getMessaggioAngeloCustode());
        return messaggio;
    }

    private String getMessaggioAngeloCustode()
    {
        return "Inoltre, essendo il " + tipoLupo + " Amato, muore " + nomeAngeloCustode.get() + " (Angelo custode).";
    }

    private boolean isAngeloCustodePresente() { return nomeAngeloCustode.isPresent(); }

    private String getMessaggioPrincipale()
    {
        String messaggio;
        if(isLupoSolitario()) messaggio = getMessaggioLupoSolitario();
        else messaggio = getMessaggioLupoBranco();
        return messaggio;
    }

    private boolean isLupoSolitario() { return tipoLupo.equals("Lupo solitario"); }

    private String getMessaggioLupoBranco()
    {
        return
            "Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (" + nomeCappuccettoRosso + ") riconosce il " + tipoLupo +
            " (" + nomeLupo + ").";
    }

    private String getMessaggioLupoSolitario()
    {
        return nomeLupo + " è il Lupo solitario. Cappuccetto rosso (" + nomeCappuccettoRosso + ") si sveglia e lo riconosce.";
    }

}