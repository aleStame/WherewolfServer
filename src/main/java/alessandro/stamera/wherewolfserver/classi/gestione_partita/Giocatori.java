package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreAlfabetico;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreVoti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import java.util.*;

import static java.util.stream.Collectors.toMap;
import java.util.Map.Entry;

public class Giocatori
{

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

    public boolean isAngeloCustodePresente() { return cercaAngeloCustode().isPresent(); }

    public String getNomeAngeloCustode() { return cercaAngeloCustode().get().getKey(); }

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

    public boolean isContadinoMostroPresente() { return cercaContadinoMostro().isPresent(); }

    public String getNomeContadinoMostro() { return cercaContadinoMostro().get().getKey(); }

    public boolean isContadinoMostro(String nome) { return getRuolo(nome).isContadinoMostro(); }

    public boolean isRomeo(String nome) { return getRuolo(nome).isRomeo(); }

    public void romeizzazione(String nome) { getRuolo(nome).romeizzazione(); }

    public boolean isNosferatuPresente() { return cercaNosferatu().isPresent(); }

    public String getNomeNosferatu() { return cercaNosferatu().get().getKey(); }

    private Optional<Entry<String, Ruolo>> cercaNosferatu()
    {
        return giocatori.entrySet().stream().filter(elemento -> elemento.getValue().isNosferatu()).findAny();
    }

    private Optional<Entry<String, Ruolo>> cercaContadinoMostro()
    {
        return giocatori.entrySet().stream().filter(elemento -> elemento.getValue().isContadinoMostro()).findAny();
    }

    private boolean isCitta(String nome) { return getRuolo(nome).isCitta(); }

    private Set<String> getChiavi() { return giocatori.keySet(); }

    private Optional<Entry<String, Ruolo>> cercaAngeloCustode()
    {
        return giocatori.entrySet().stream().filter(elemento -> elemento.getValue().isAngeloCustode()).findAny();
    }

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