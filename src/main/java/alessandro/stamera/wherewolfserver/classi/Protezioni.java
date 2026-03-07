package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.Fazione.values;

public final class Protezioni
{

    private final List<Fazione> fazioni;

    public Protezioni() { fazioni = new ArrayList<>(); }

    public void aggiungiProtezione(Categoria categoria)
    {
        for(Fazione fazione : values()) if(fazione.getCategoria() == categoria) fazioni.add(fazione);
    }

    public void aggiungiProtezione(Fazione fazione) { }

    public boolean isPresente(Fazione fazione) { return fazioni.contains(fazione); }

    public void perdiProtezioni() { fazioni.clear(); }

}
