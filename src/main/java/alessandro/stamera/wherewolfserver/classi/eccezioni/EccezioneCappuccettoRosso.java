package alessandro.stamera.wherewolfserver.classi.eccezioni;

import org.springframework.lang.NonNull;

import java.util.Optional;

public class EccezioneCappuccettoRosso extends IllegalStateException
{

    private final String tipoLupo, nomeLupo, nomeCappuccettoRosso;

    private final Optional<String> nomeAngeloCustode;

    public EccezioneCappuccettoRosso(String tipoLupo, String nomeLupo, String nomeCappuccettoRosso)
    {
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
        this.nomeCappuccettoRosso = nomeCappuccettoRosso;
        nomeAngeloCustode = Optional.empty();
    }

    public EccezioneCappuccettoRosso(String tipoLupo, String nomeLupo, String nomeCappuccettoRosso, String nomeAngeloCustode)
    {
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
        this.nomeCappuccettoRosso = nomeCappuccettoRosso;
        this.nomeAngeloCustode = Optional.of(nomeAngeloCustode);
    }

    @Override public String getMessage()
    {
        String messaggio = getMessaggioPrincipale();
        if(nomeAngeloCustode.isPresent())
            messaggio += "\nInoltre, essendo il " + tipoLupo + " Amato, muore " + nomeAngeloCustode.get() + " (Angelo custode).";
        return messaggio;
    }

    private String getMessaggioPrincipale()
    {
        String messaggio;
        if(tipoLupo.equals("Lupo solitario")) messaggio = getMessaggioLupoSolitario();
        else messaggio = getMessaggioLupoBranco();
        return messaggio;
    }

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