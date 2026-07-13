package alessandro.stamera.wherewolfserver.classi.eccezioni;

public final class EccezioneProgenizzazioneNonRiuscitaConGhoul extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima, ruoloProgenizzatore, nomeGhoul;

    public EccezioneProgenizzazioneNonRiuscitaConGhoul
    (
        String ruoloVittima, String nomeVittima, String ruoloProgenizzatore, String nomeGhoul
    )
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.ruoloProgenizzatore = ruoloProgenizzatore;
        this.nomeGhoul = nomeGhoul;
    }

    @Override public String getMessage()
    {
        return
            "Il tentativo di " + getSostantivo() + " del " + ruoloVittima + " (" + nomeVittima + ") causa la morte del Ghoul (" + nomeGhoul +
            ").\nAvvisa " + nomeGhoul + " della sua morte.";
    }

    private String getSostantivo()
    {
        String sostantivo = "vampirizzazione";
        if(ruoloProgenizzatore.equals("Nosferatu")) sostantivo = "progenizzazione";
        return sostantivo;
    }

}