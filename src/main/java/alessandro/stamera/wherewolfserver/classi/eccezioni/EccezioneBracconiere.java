package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneBracconiere extends IllegalStateException
{

    public EccezioneBracconiere(String nomeBracconiere, String nomeVittima)
    {
        super("Il Bracconiere (" + nomeBracconiere + ") ha usato il suo potere, pertanto l'attacco a " + nomeVittima + " fallisce.");
    }

}