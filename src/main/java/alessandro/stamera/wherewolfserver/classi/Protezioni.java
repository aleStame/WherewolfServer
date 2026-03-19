package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;

public final class Protezioni
{

    private final List<Ruolo> ruoli;

    public Protezioni() { ruoli = new ArrayList<>(); }

    public void aggiungiProtezioneCreatureOmbra()
    {
        List<Ruolo> creatureOmbra = new ArrayList<>();
        RuoliFactory factory = new RuoliFactory();
        for(int i = 0; i < factory.getNumeroRuoli(); i++)
        {
            Ruolo ruolo = factory.getRuolo(factory.getNome(i));
            if(ruolo.getCategoria() == CREATURE_OMBRA || ruolo.isTrattoPresente(CREATURA_OMBRA)) creatureOmbra.add(ruolo);
        }
        aggiungiProtezione(toArray(creatureOmbra, Ruolo[]::new));
    }

    public void aggiungiProtezioneLupi() { aggiungiProtezione(new RuoliFactory().getLupi()); }

    public boolean isPresente(Ruolo ruolo) { return ruoli.contains(ruolo); }

    public void perdiProtezioni() { ruoli.clear(); }

    public void aggiungiProtezione(Ruolo... ruoli)
    {
        for(Ruolo ruolo : ruoli) if(!this.ruoli.contains(ruolo)) this.ruoli.add(ruolo);
    }

    private <T> T[] toArray(List<T> lista, IntFunction<T[]> generatore) { return lista.toArray(generatore); }

}