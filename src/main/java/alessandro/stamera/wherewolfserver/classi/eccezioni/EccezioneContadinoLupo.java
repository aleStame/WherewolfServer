package alessandro.stamera.wherewolfserver.classi.eccezioni;

public class EccezioneContadinoLupo extends IllegalArgumentException
{

    private final String nomeContadino, fazione;

    public EccezioneContadinoLupo(String nomeContadino, String fazione)
    {
        this.nomeContadino = nomeContadino;
        this.fazione = fazione;
    }

    @Override public String getMessage()
    {
        String messaggio;
        if(fazione.equals("Lupi del branco")) messaggio = getMessaggioLupiBranco();
        else messaggio =
            "Il Contadino discendente dei lupi (" + nomeContadino + ") è stato attaccato dal Lupo solitario, pertanto anche lui diventa tale.\n" +
            "Sveglia " + nomeContadino + " e fagli riconoscere il Lupo solitario che lo ha attaccato.";
        return messaggio;
    }

    private String getMessaggioLupiBranco()
    {
        return
            "Il Contadino discendente dei lupi (" + nomeContadino + ") è stato attaccato dai Lupi del branco, pertanto adesso fa parte della loro " +
            "fazione.\nSveglia " + nomeContadino + " e fagli riconoscere gli altri lupi.";
    }

}
