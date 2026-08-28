package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneCacciatore extends IllegalStateException
{

    public EccezioneCacciatore(String nomeCacciatore)
    {
        super(nomeCacciatore + " è il Cacciatore ed è protetto dall'attacco del lupo ex Nonna.\nAvvisa i lupi dell'attacco fallito.");
    }

}