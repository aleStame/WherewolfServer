package alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import java.util.Comparator;
import java.util.Map.Entry;

public final class ComparatoreVoti implements Comparator<Entry<String, Ruolo>>
{

    @Override public int compare(Entry<String, Ruolo> giocatore1, Entry<String, Ruolo> giocatore2)
    {
        //return giocatore2.getValue().getNumeroVoti() - giocatore1.getValue().getNumeroVoti();
        return 0;
    }
}