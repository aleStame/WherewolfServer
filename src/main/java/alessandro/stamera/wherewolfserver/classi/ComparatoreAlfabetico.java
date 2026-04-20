package alessandro.stamera.wherewolfserver.classi;

import java.util.Comparator;
import java.util.Map.Entry;

public final class ComparatoreAlfabetico implements Comparator<Entry<String, Ruolo>>
{

    @Override public int compare(Entry<String, Ruolo> giocatore1, Entry<String, Ruolo> giocatore2)
    {
        return giocatore1.getKey().compareTo(giocatore2.getKey());
    }
}