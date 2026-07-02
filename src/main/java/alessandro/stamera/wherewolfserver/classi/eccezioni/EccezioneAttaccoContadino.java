package alessandro.stamera.wherewolfserver.classi.eccezioni;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.TipoContadino;

public class EccezioneAttaccoContadino extends IllegalStateException
{

    public EccezioneAttaccoContadino(TipoContadino tipoContadino, String nomeContadino, String nomeLupoAttaccante) { }

}