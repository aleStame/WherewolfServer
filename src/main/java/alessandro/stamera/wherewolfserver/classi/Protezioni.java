package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static java.util.Arrays.stream;

public final class Protezioni
{

    private final List<Ruolo> ruoli;

    public Protezioni() { ruoli = new ArrayList<>(); }

    public void aggiungiProtezioneCreatureOmbra() { aggiungiProtezione(FACTORY.getCreatureOmbra()); }

    public void aggiungiProtezioneLupi() { aggiungiProtezione(FACTORY.getLupi()); }

    public boolean isPresente(Ruolo ruolo) { return ruoli.contains(ruolo); }

    public void perdiProtezioni() { ruoli.clear(); }

    public void aggiungiProtezione(Ruolo... ruoli)
    {
        for(Ruolo ruolo : ruoli) if(!this.ruoli.contains(ruolo)) this.ruoli.add(ruolo);
    }

    public boolean isNegromantePresente() { return ruoli.stream().anyMatch(Ruolo::isNegromante); }

    public boolean isProtezioneLupiPresente() { return stream(FACTORY.getLupi()).allMatch(ruoli::contains); }

    public boolean isNosferatuPresente() { return false; }

}