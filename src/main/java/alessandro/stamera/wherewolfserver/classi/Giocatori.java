package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Giocatori
{

    private final Map<String, Ruolo> giocatori;

    public Giocatori() { giocatori = new LinkedHashMap<>(); }

    public void aggiungiGiocatore(String nome, Ruolo ruolo) { giocatori.put(nome, ruolo); }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void eliminaGiocatore(String nome) { giocatori.remove(nome); }

    public void incrementaVoti(String nome, int voti) { getRuolo(nome).incrementaVoti(voti); }

    public int getNumeroVoti(String nome) { return getRuolo(nome).getNumeroVoti(); }

    public Giocatori getBallottaggio()
    {
        Ballottaggio ballottaggio = new Ballottaggio();
        List<Entry<String, Ruolo>> giocatori = new java.util.ArrayList<>(this.giocatori.entrySet().stream().toList());
        giocatori.sort(new ComparatoreVoti());
        this.giocatori.clear();
        for(Entry<String, Ruolo> giocatore : giocatori) this.giocatori.put(giocatore.getKey(), giocatore.getValue());
        List<String> chiavi = this.giocatori.keySet().stream().toList();
        for(int i = 0; i < 2; i++)
        {
            String chiave = chiavi.get(i);
            ballottaggio.aggiungiGiocatore(chiave, getRuolo(chiave));
        }
        return ballottaggio;
    }

    private Ruolo getRuolo(String nome) { return giocatori.get(nome); }

}