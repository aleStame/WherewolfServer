package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static java.util.Arrays.stream;
import java.util.function.Predicate;

public final class Protezioni
{

    private final List<Ruolo> ruoli;

    public Protezioni() { ruoli = new ArrayList<>(); }

    public void aggiungiProtezioneCreatureOmbra()
    {
        aggiungiProtezione(FACTORY.getCreatureOmbra());
        aggiungiProtezione(FACTORY.getRuolo("Contadino discendente dei lupi"));
    }

    public void aggiungiProtezioneLupi() { aggiungiProtezione(FACTORY.getLupi()); }

    public boolean isPresente(Ruolo ruolo) { return ruoli.contains(ruolo); }

    public void perdiProtezioni() { ruoli.clear(); }

    public void aggiungiProtezione(Ruolo... ruoli)
    {
        for(Ruolo ruolo : ruoli) if(!this.ruoli.contains(ruolo)) this.ruoli.add(ruolo);
    }

    public boolean isNegromantePresente() { return cercaRuolo(Ruolo::isNegromante); }

    public boolean isProtezioneLupiPresente() { return stream(FACTORY.getLupi()).allMatch(ruoli::contains); }

    public boolean isNosferatuPresente() { return cercaRuolo(Ruolo::isNosferatu); }

    public boolean isPossedutoPresente() { return cercaRuolo(Ruolo::isPosseduto); }

    public boolean isVampiroPresente() { return cercaRuolo(Ruolo::isVampiro); }

    public void perdiProtezione(Ruolo ruolo) { ruoli.remove(ruolo); }

    private boolean cercaRuolo(Predicate<Ruolo> condizione) { return ruoli.stream().anyMatch(condizione); }

}