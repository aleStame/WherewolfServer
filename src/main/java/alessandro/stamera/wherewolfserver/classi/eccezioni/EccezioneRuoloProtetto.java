package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneRuoloProtetto extends IllegalArgumentException
{

    public EccezioneRuoloProtetto(String ruoloVittima, String nomeVittima)
    {
        super
        (
            "Impossible uccidere la Ladra (" + nomeVittima + ") perché protetta almeno una volta dalle Creature dell'ombra.\nAvvisa i lupi " +
            "dell'attacco fallito."
        );
    }

}
