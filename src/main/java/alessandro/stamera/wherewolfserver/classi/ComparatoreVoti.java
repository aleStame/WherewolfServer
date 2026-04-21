package alessandro.stamera.wherewolfserver.classi;

import java.util.Comparator;
import java.util.Map.Entry;

public final class ComparatoreVoti implements Comparator<Entry<String, Ruolo>>
{

    @Override public int compare(Entry<String, Ruolo> giocatore1, Entry<String, Ruolo> giocatore2)
    {
        return giocatore2.getValue().getNumeroVoti() - giocatore1.getValue().getNumeroVoti();
    }
}