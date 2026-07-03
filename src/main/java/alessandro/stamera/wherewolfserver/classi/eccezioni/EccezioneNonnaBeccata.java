package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneNonnaBeccata extends IllegalArgumentException
{

    public EccezioneNonnaBeccata(String nomeLupo, String tipoLupo, String nomeNonna)
    {
        super
        (
            "Il " + tipoLupo + " (" + nomeLupo + ") ha beccato la Nonna (" + nomeNonna + ").\nSveglia " + nomeNonna + " e avvisa i due giocatori " +
            "che " + nomeLupo+ " è eliminato e che " + nomeNonna + " è il " + tipoLupo + "."
        );
    }

}