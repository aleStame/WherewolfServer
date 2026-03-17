package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static java.util.Arrays.stream;

public final class Protezioni
{

    private final List<Ruolo> ruoli;

    public Protezioni() { ruoli = new ArrayList<>(); }

    public void aggiungiProtezioneCreatureOmbra() { aggiungiProtezione(CREATURE_OMBRA); }

    public void aggiungiProtezione(Categoria categoria)
    {
        List<Fazione> fazioni = Stream.of(Fazione.values()).filter(fazione -> (fazione).getCategoria() == categoria).toList();
        aggiungiProtezione(toArray(fazioni, Fazione[]::new));
    }

    public void aggiungiProtezione(Fazione... fazioni) { aggiungiProtezione(toArray(filtraRuoli(fazioni), Ruolo[]::new)); }

    public void aggiungiProtezioneLupi() { aggiungiProtezione(getLupi()); }

    public boolean isPresente(Ruolo ruolo) { return ruoli.contains(ruolo); }

    public void perdiProtezioni() { ruoli.clear(); }

    private List<Ruolo> filtraRuoli(Fazione... fazioni)
    {
        return getRuoli().stream().filter(ruolo -> stream(fazioni).anyMatch(fazione -> fazione == ruolo.getFazione())).toList();
    }

    private void aggiungiProtezione(Ruolo... ruoli)
    {
        for(Ruolo ruolo : ruoli) if(!this.ruoli.contains(ruolo)) this.ruoli.add(ruolo);
    }

    private Ruolo[] getLupi() { return toArray(getRuoli(), Ruolo[]::new); }

    private <T> T[] toArray(List<T> lista, IntFunction<T[]> generatore) { return lista.toArray(generatore); }

    private List<Ruolo> getRuoli() { return stream(IstanzaRuolo.values()).map(IstanzaRuolo::getRuolo).filter(Ruolo::isLupo).toList(); }

}
