package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import java.util.Map.Entry;

public class Giocatori
{

    private final Map<String, Ruolo> giocatori;

    public Giocatori() { giocatori = new LinkedHashMap<>(); }

    public void aggiungiGiocatore(String nome, Ruolo ruolo) { giocatori.put(nome, ruolo); }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void eliminaGiocatore(String nome) { giocatori.remove(nome); }

    public void incrementaVoti(String nome, int voti)
    {
        getRuolo(nome).incrementaVoti(voti);
        giocatori.entrySet().stream().sorted(new ComparatoreVoti())
            .collect(toMap(Entry::getKey, Entry::getValue, (giocatore1, giocatore2) -> giocatore1, LinkedHashMap::new));
    }

    public int getNumeroVoti(String nome) { return getRuolo(nome).getNumeroVoti(); }

    public Ruolo getRuolo(String nome) { return giocatori.get(nome); }

    public String getNomeGiocatore(int posizione) { return giocatori.keySet().stream().toList().get(posizione); }

}