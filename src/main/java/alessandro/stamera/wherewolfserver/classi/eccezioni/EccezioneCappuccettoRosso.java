package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneCappuccettoRosso extends IllegalStateException
{

    private final String tipoLupo, nomeLupo, nomeCappuccettoRosso;

    public EccezioneCappuccettoRosso(String tipoLupo, String nomeLupo, String nomeCappuccettoRosso)
    {
        this.tipoLupo = tipoLupo;
        this.nomeLupo = nomeLupo;
        this.nomeCappuccettoRosso = nomeCappuccettoRosso;
    }

    @Override public String getMessage()
    {
        String messaggio;
        if(tipoLupo.equals("Lupo solitario"))
            messaggio = nomeLupo + " è il Lupo solitario. Cappuccetto rosso (" + nomeCappuccettoRosso + ") si sveglia e lo riconosce";
        else messaggio =
            "Dal momento che non ci sono altri lupi del branco, il Cappuccetto rosso (" + nomeCappuccettoRosso + ") riconosce il " + tipoLupo +
            " (" + nomeLupo + ").";
        return messaggio;
    }

}