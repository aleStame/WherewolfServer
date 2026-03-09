package alessandro.stamera.wherewolfserver.classi;

import java.util.ArrayList;
import java.util.List;
import static alessandro.stamera.wherewolfserver.classi.Fazione.values;

public final class Protezioni
{

    private final List<Fazione> fazioni;

    private final List<Ruolo> ruoli;

    public Protezioni()
    {
        fazioni = new ArrayList<>();
        ruoli = new ArrayList<>();
    }

    public void aggiungiProtezione(Categoria categoria)
    {
        for(Fazione fazione : values()) if(fazione.getCategoria() == categoria) aggiungiProtezione(fazione);
    }

    public void aggiungiProtezione(Fazione fazione) { fazioni.add(fazione); }

    public boolean isPresente(Fazione fazione) { return fazioni.contains(fazione); }

    public boolean isPresente(Ruolo ruolo)
    {
        return ruoli.stream().anyMatch(elemento -> elemento.getNome().equals(ruolo.getNome()));
    }

    public void perdiProtezioni() { fazioni.clear(); }

    public void aggiungiLupi() { for(Ruolo ruolo : new Ruoli()) if(ruolo.isLupo()) ruoli.add(ruolo); }

}
