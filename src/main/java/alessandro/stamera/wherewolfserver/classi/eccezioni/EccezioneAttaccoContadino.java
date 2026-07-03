package alessandro.stamera.wherewolfserver.classi.eccezioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;

public final class EccezioneAttaccoContadino extends IllegalArgumentException
{

    private final TipoContadino tipoContadino;

    private final String nomeContadino, nomeLupoAttaccante;

    public EccezioneAttaccoContadino(TipoContadino tipoContadino, String nomeContadino, String nomeLupoAttaccante)
    {
        this.tipoContadino = tipoContadino;
        this.nomeContadino = nomeContadino;
        this.nomeLupoAttaccante = nomeLupoAttaccante;
    }

    @Override public String getMessage()
    {
        return
            "L'attacco al " + tipoContadino + " (" + nomeContadino + ") causa la morte anche del lupo attaccante (" + nomeLupoAttaccante +
            ").\nAvvisa entrambi i giocatori della loro morte.";
    }

}