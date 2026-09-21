package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneCappuccettoRossoNonna extends IllegalArgumentException
{

    public EccezioneCappuccettoRossoNonna(String nomeNonna, String nomeCappuccettoRosso)
    {
        super
        (
            "Fino a che la Nonna (" + nomeNonna + ") è in gioco, Cappuccetto Rosso (" + nomeCappuccettoRosso + ") non può essere attaccata dai " +
            "lupi.\nAvvisali dell'attacco fallito"
        );
    }

}
