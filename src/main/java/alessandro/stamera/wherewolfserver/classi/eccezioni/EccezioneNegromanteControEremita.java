package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneNegromanteControEremita extends RuntimeException
{

    public EccezioneNegromanteControEremita(String nomeEremita, String nomeNegromante)
    {
        super(nomeEremita + " è l'Eremita.\nAvvisa " + nomeNegromante + " (Negromante) che non può maledirlo, e che indicare qualcun altro");
    }

}