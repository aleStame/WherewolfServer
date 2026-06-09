package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreAlfabetico;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreVoti;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Comparator;
import java.util.Collection;
import static java.util.stream.Collectors.toMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
        getRuoli().forEach(Ruolo::annullaVoti);
        ordinaAlfabeticamente();
    }

    public void annullaVoti(String nome) { getRuolo(nome).annullaVoti(); }

    public Ruolo getRuolo(String nome) { return giocatori.get(nome); }

    public String getNomeGiocatore(int posizione) { return giocatori.keySet().stream().toList().get(posizione); }

    public boolean isAmato(String nome) { return getRuolo(nome).isAmato(); }

    public boolean isAngeloCustodePresente() { return cercaAngeloCustode().isPresent(); }

    public String getNomeAngeloCustode() { return cercaAngeloCustode().get().getKey(); }

    public boolean isAngeloCustode(String nome) { return getRuolo(nome).isAngeloCustode(); }

    public void resettaAmato() { getRuoli().forEach(Ruolo::resettaAmato); }

    public boolean isPresente(String nome) { return giocatori.containsKey(nome); }

    public boolean isCriminale(String nome) { return getRuolo(nome).isCriminale(); }

    public void segnalazioneInquisitore(String nome) { getRuolo(nome).segnalazioneInquisitore(); }

    public boolean isInquisito(String nome) { return getRuolo(nome).isInquisito(); }

    public boolean isSegnalatoAzzeccagarbugli(String nome) { return getRuolo(nome).isSegnalatoAzzeccagarbugli(); }

    public void segnalazioneBoia(String nome) { getRuolo(nome).segnalazioneBoia(); }

    public boolean isSegnalatoBoia(String nome) { return getRuolo(nome).isSegnalatoBoia(); }

    public void annullaSegnalazioneBoia(String nome) { getRuolo(nome).annullaSegnalazioneBoia(); }

    public int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

    public boolean isOratorePresente() { return getStreamRuoli().anyMatch(Ruolo::isOratore); }

    public int getNumeroRuoliCitta() { return (int)getStreamRuoli().filter(Ruolo::isCitta).count(); }

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
        return cercaRuolo(ruolo -> ruolo.getValue().isNosferatu());
    }

    private Optional<Entry<String, Ruolo>> cercaContadinoMostro()
    {
        return cercaRuolo(ruolo -> ruolo.getValue().isContadinoMostro());
    }

    private Optional<Entry<String, Ruolo>> cercaAngeloCustode()
    {
        return cercaRuolo(ruolo -> ruolo.getValue().isAngeloCustode());
    }

    private Optional<Entry<String, Ruolo>> cercaRuolo(Predicate<Entry<String, Ruolo>> predicato)
    {
        return getGiocatori().filter(predicato).findAny();
    }

    private void ordinaGiocatori(Comparator<Entry<String, Ruolo>> comparatore)
    {
        Map<String, Ruolo> copia =
            getGiocatori().sorted(comparatore).collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        giocatori.clear();
        for(String nome : copia.keySet()) giocatori.put(nome, copia.get(nome));
    }

    private Stream<Entry<String, Ruolo>> getGiocatori() { return giocatori.entrySet().stream(); }

    private void ordinaAlfabeticamente() { ordinaGiocatori(new ComparatoreAlfabetico()); }

    private Stream<Ruolo> getStreamRuoli() { return getRuoli().stream(); }

    private Collection<Ruolo> getRuoli() { return giocatori.values(); }

}