package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneCacciatore extends IllegalStateException
{

    public EccezioneCacciatore(String nomeCacciatore)
    {
        super(nomeCacciatore + " è il Cacciatore ed è protetto dall'attacco del lupo ex Nonna.\nAvvisa i lupi dell'attacco fallito.");
    }

    public EccezioneCacciatore(String nomeLupo, String nomeCacciatore)
    {
        super(nomeLupo + " è l'ultimo lupo rimasto in gioco.\nAvvisalo dell'attacco fallito al Cacciatore (" + nomeCacciatore + ").");
    }

}