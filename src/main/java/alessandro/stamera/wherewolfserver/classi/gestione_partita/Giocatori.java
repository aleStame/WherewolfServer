package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreAlfabetico;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreVoti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import java.util.Map.Entry;
import java.util.Set;

public class Giocatori
{

    private static final int NON_TROVATO = -1;

    private final Map<String, Ruolo> giocatori;

    public Giocatori() { giocatori = new LinkedHashMap<>(); }

    public void aggiungiGiocatore(String nome, Ruolo ruolo)
    {
        giocatori.put(nome, ruolo);
        ordinaAlfabeticamente();
    }

    public int getNumeroVoti(String nome) { return getRuolo(nome).getNumeroVoti(); }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void eliminaGiocatore(String nome) { giocatori.remove(nome); }

    public void incrementaVoti(String nome, int voti)
    {
        getRuolo(nome).incrementaVoti(voti);
        ordinaGiocatori(new ComparatoreVoti());
    }

    public void annullaVoti()
    {
        for(String nome : getChiavi()) annullaVoti(nome);
        ordinaAlfabeticamente();
    }

    public void annullaVoti(String nome) { getRuolo(nome).annullaVoti(); }

    public Ruolo getRuolo(String nome) { return giocatori.get(nome); }

    public String getNomeGiocatore(int posizione) { return getChiavi().stream().toList().get(posizione); }

    public boolean isAmato(String nome) { return getRuolo(nome).isAmato(); }

    public boolean isAngeloCustodePresente() { return getPosizioneAngeloCustode() != NON_TROVATO; }

    public String getNomeAngeloCustode() { return getNomeGiocatore(getPosizioneAngeloCustode()); }

    public boolean isAngeloCustode(String nome) { return getRuolo(nome).isAngeloCustode(); }

    public void resettaAmato() { for(String chiave : getChiavi()) getRuolo(chiave).resettaAmato(); }

    public boolean isPresente(String nome) { return giocatori.containsKey(nome); }

    public boolean isCriminale(String nome) { return getRuolo(nome).isCriminale(); }

    public void segnalazioneInquisitore(String nome) { getRuolo(nome).segnalazioneInquisitore(); }

    public boolean isInquisito(String nome) { return getRuolo(nome).isInquisito(); }

    public boolean isSegnalatoAzzeccagarbugli(String nome) { return getRuolo(nome).isSegnalatoAzzeccagarbugli(); }

    public void segnalazioneBoia(String nome) { getRuolo(nome).segnalazioneBoia(); }

    public boolean isSegnalatoBoia(String nome) { return getRuolo(nome).isSegnalatoBoia(); }

    public void annullaSegnalazioneBoia(String nome) { getRuolo(nome).annullaSegnalazioneBoia(); }

    public int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

    public boolean isOratorePresente() { return getChiavi().stream().anyMatch(this::isOratore); }

    public int getNumeroRuoliCitta()
    {
        int numeroCitta = 0;
        for(String chiave : getChiavi()) if(isCitta(chiave)) numeroCitta++;
        return numeroCitta;
    }

    public boolean isOratore(String nome) { return getRuolo(nome).isOratore(); }

    public boolean isContadinoMostroPresente() { return giocatori.values().stream().anyMatch(Ruolo::isContadinoMostro); }

    public String getNomeContadinoMostro()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(getRuolo(getNomeGiocatore(i)).isContadinoMostro())
            posizione = i;
        return getNomeGiocatore(posizione);
    }

    private boolean isCitta(String nome) { return getRuolo(nome).isCitta(); }

    private Set<String> getChiavi() { return giocatori.keySet(); }

    private int getPosizioneAngeloCustode()
    {
        int posizione = NON_TROVATO;
        for(int i = 0; i < getNumeroGiocatori() && posizione == NON_TROVATO; i++) if(isAngeloCustode(i)) posizione = i;
        return posizione;
    }

    private boolean isAngeloCustode(int posizione) { return isAngeloCustode(getNomeGiocatore(posizione)); }

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