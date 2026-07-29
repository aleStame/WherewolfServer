package alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.Giocatore;
import java.util.Comparator;
import java.util.Map.Entry;

public final class ComparatoreVoti implements Comparator<Entry<String, Giocatore>>
{

    @Override public int compare(Entry<String, Giocatore> giocatore1, Entry<String, Giocatore> giocatore2)
    {
        return giocatore2.getValue().getNumeroVoti() - giocatore1.getValue().getNumeroVoti();
    }
}