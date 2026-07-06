package alessandro.stamera.wherewolfserver.classi.eccezioni;

import java.util.Optional;

public final class EccezioneProgenizzazioneNonRiuscita extends IllegalArgumentException
{

    private final String ruoloVittima, nomeVittima;

    private final Optional<String> nomeAngeloCustode, nomeVampiroAmato, nomeGhoul;

    public EccezioneProgenizzazioneNonRiuscita
    (
        String ruoloVittima, String nomeVittima, String nomeAngeloCustode, String nomeVampiroAmato
    )
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeAngeloCustode = inizializzaStringa(nomeAngeloCustode);
        this.nomeVampiroAmato = inizializzaStringa(nomeVampiroAmato);
        this.nomeGhoul = inizializzaStringaVuota();
    }

    public EccezioneProgenizzazioneNonRiuscita(String ruoloVittima, String nomeVittima, String nomeGhoul)
    {
        this.ruoloVittima = ruoloVittima;
        this.nomeVittima = nomeVittima;
        this.nomeAngeloCustode = inizializzaStringaVuota();
        this.nomeVampiroAmato = inizializzaStringaVuota();
        this.nomeGhoul = inizializzaStringa(nomeGhoul);
    }

    @Override public String getMessage()
    {
        String messaggio;
        if(isEliminatoAngeloCustode()) messaggio = getMessaggioErroreAngeloCustode();
        else messaggio = getMessaggioErroreGhoul();
        return messaggio;
    }

    private Optional<String> inizializzaStringa(String stringa) { return Optional.of(stringa); }

    private Optional<String> inizializzaStringaVuota() { return Optional.empty(); }

    private boolean isEliminatoAngeloCustode() { return nomeAngeloCustode.isPresent() && nomeVampiroAmato.isPresent(); }

    private String getMessaggioErroreAngeloCustode()
    {
        String nomeAngeloCustode = this.nomeAngeloCustode.get();
        return
            "Il tentativo di vampirizzazione del " + ruoloVittima + " (" + nomeVittima + ") causa la morte dell'Angelo custode (" +
            nomeAngeloCustode + ") del Vampiro amato (" + nomeVampiroAmato.get() + ").\nAvvisa " + nomeAngeloCustode + " della sua morte.";
    }

    private String getMessaggioErroreGhoul()
    {
        String nomeGhoul = this.nomeGhoul.get();
        return
            "Il tentativo di vampirizzazione del " + ruoloVittima + " (" + nomeVittima + ") causa la morte del Ghoul (" + nomeGhoul + ").\n" +
            "Avvisa " + nomeGhoul + " della sua morte.";
    }

}