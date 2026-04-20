package alessandro.stamera.wherewolfserver.classi;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import java.util.Map.Entry;
import java.util.Set;

public class Giocatori
{

    private final Map<String, Ruolo> giocatori;

    public Giocatori() { giocatori = new LinkedHashMap<>(); }

    public void aggiungiGiocatore(String nome, Ruolo ruolo)
    {
        giocatori.put(nome, ruolo);
        ordinaAlfabeticamente();
    }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void eliminaGiocatore(String nome) { giocatori.remove(nome); }

    public void incrementaVoti(String nome, int voti)
    {
        getRuolo(nome).incrementaVoti(voti);
        ordinaGiocatori(new ComparatoreVoti());
    }

    public int getNumeroVoti(String nome) { return getRuolo(nome).getNumeroVoti(); }

    public void annullaVoti()
    {
        for(String nome : getChiavi()) annullaVoti(nome);
        ordinaAlfabeticamente();
    }

    public void annullaVoti(String nome) { getRuolo(nome).annullaVoti(); }

    public Ruolo getRuolo(String nome) { return giocatori.get(nome); }

    public String getNomeGiocatore(int posizione) { return getChiavi().stream().toList().get(posizione); }

    private Set<String> getChiavi() { return giocatori.keySet(); }

    private void ordinaGiocatori(Comparator<Entry<String, Ruolo>> comparatore)
    {
        Map<String, Ruolo> copia =
            giocatori.entrySet().stream().sorted(comparatore)
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        giocatori.clear();
        for(String nome : copia.keySet()) giocatori.put(nome, copia.get(nome));
    }

    private void ordinaAlfabeticamente() { ordinaGiocatori(new ComparatoreAlfabetico()); }

}