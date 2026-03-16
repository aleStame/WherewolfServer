package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
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
        Fazione[] risultato = new Fazione[fazioni.size()];
        for(int i = 0; i < risultato.length; i++) risultato[i] = fazioni.get(i);
        aggiungiProtezione(risultato);
    }

    public void aggiungiProtezione(Fazione... fazioni)
    {
        List<Ruolo> ruoliAdatti =
            getRuoli().stream().filter(ruolo -> stream(fazioni).anyMatch(fazione -> fazione == ruolo.getFazione())).toList();
        Ruolo[] risultato = new Ruolo[ruoliAdatti.size()];
        ruoliAdatti.toArray(risultato);
        aggiungiProtezione(risultato);
    }

    public void aggiungiProtezioneLupi() { aggiungiProtezione(getLupi()); }

    public boolean isPresente(Ruolo ruolo) { return ruoli.contains(ruolo); }

    public void perdiProtezioni() { ruoli.clear(); }

    private void aggiungiProtezione(Ruolo... ruoli)
    {
        for(Ruolo ruolo : ruoli) if(!this.ruoli.contains(ruolo)) this.ruoli.add(ruolo);
    }

    private Ruolo[] getLupi()
    {
        List<Ruolo> lupi = getRuoli();
        Ruolo[] risultato = new Ruolo[lupi.size()];
        lupi.toArray(risultato);
        return risultato;
    }

    private List<Ruolo> getRuoli() { return stream(IstanzaRuolo.values()).map(IstanzaRuolo::getRuolo).filter(Ruolo::isLupo).toList(); }

}
