package alessandro.stamera.wherewolfserver.classi.gestione_partita;

import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreAlfabetico;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.comparatori.ComparatoreVoti;
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

    private final Map<String, Giocatore> giocatori;

    public Giocatori() { giocatori = new LinkedHashMap<>(); }

    public void aggiungiGiocatore(String nome, Giocatore giocatore)
    {
        giocatori.put(nome, giocatore);
        ordinaAlfabeticamente();
    }

    public int getNumeroVoti(String nome) { return getGiocatore(nome).getNumeroVoti(); }

    public int getNumeroGiocatori() { return giocatori.size(); }

    public void eliminaGiocatore(String nome) { giocatori.remove(nome); }

    public void incrementaVoti(String nome, int voti)
    {
        getGiocatore(nome).incrementaVoti(voti);
        ordinaGiocatori(new ComparatoreVoti());
    }

    public void annullaVoti()
    {
        getRuoli().forEach(Giocatore::annullaVoti);
        ordinaAlfabeticamente();
    }

    public void annullaVoti(String nome) { getGiocatore(nome).annullaVoti(); }

    public String getNomeGiocatore(int posizione) { return giocatori.keySet().stream().toList().get(posizione); }

    public boolean isAmato(String nome)
    {
        boolean esito = false;
        if(isPresente(nome)) esito = getGiocatore(nome).isAmato();
        return esito;
    }

    public boolean isAngeloCustodePresente() { return cercaAngeloCustode().isPresent(); }

    public String getNomeAngeloCustode() { return cercaAngeloCustode().get().getKey(); }

    public boolean isAngeloCustode(String nome) { return getGiocatore(nome).isAngeloCustode(); }

    public void resettaAmato() { getRuoli().forEach(Giocatore::annullaProtezioneAngeloCustode); }

    public boolean isPresente(String nome) { return giocatori.containsKey(nome); }

    public boolean isCriminale(String nome) { return getGiocatore(nome).isCriminale(); }

    public void segnalazioneBoia(String nome) { getGiocatore(nome).getRuolo().segnalazioneBoia(); }

    public boolean isSegnalatoBoia(String nome) { return getGiocatore(nome).getRuolo().isSegnalatoBoia(); }

    public void annullaSegnalazioneBoia(String nome) { getGiocatore(nome).getRuolo().annullaSegnalazioneBoia(); }

    public int getNumeroVotiPrimoClassificato() { return getNumeroVoti(getNomeGiocatore(0)); }

    public boolean isOratorePresente() { return getStreamRuoli().anyMatch(Giocatore::isOratore); }

    public int getNumeroRuoliCitta() { return (int)getStreamRuoli().filter(Giocatore::isCitta).count(); }

    public boolean isOratore(String nome) { return getGiocatore(nome).isOratore(); }

    public boolean isContadinoMostroPresente() { return cercaContadinoMostro().isPresent(); }

    public String getNomeContadinoMostro() { return cercaContadinoMostro().get().getKey(); }

    public boolean isContadinoMostro(String nome) { return getGiocatore(nome).isContadinoMostro(); }

    public boolean isRomeo(String nome) { return getGiocatore(nome).isRomeo(); }

    public void romeizzazione(String nome) { getGiocatore(nome).romeizzazione(); }

    public boolean isNosferatuPresente() { return cercaNosferatu().isPresent(); }

    public String getNomeNosferatu() { return cercaNosferatu().get().getKey(); }

    public boolean isMegera(String nome) { return getGiocatore(nome).isMegera(); }

    public String getNomeRuolo(String nomeGiocatore)
    {
        String risultato = getGiocatore(nomeGiocatore).getNomeRuolo();
        if(isContadino(nomeGiocatore)) risultato = getNomeTipoContadino(nomeGiocatore);
        return risultato;
    }

    public Giocatore getGiocatore(String nome) { return giocatori.get(nome); }

    private boolean isContadino(String nome) { return getGiocatore(nome).isContadino(); }

    private String getNomeTipoContadino(String nomeGiocatore)
    {
        String risultato = "Contadino normale";
        if(isContadinoEroe(nomeGiocatore)) risultato = "Contadino eroe";
        else if(isContadinoLupo(nomeGiocatore)) risultato = "Contadino discendente dei lupi";
        else if(isContadinoMostro(nomeGiocatore)) risultato = "Contadino mostro";
        return risultato;
    }

    private boolean isContadinoEroe(String nome) { return getGiocatore(nome).isContadinoEroe(); }

    private boolean isContadinoLupo(String nome) { return getGiocatore(nome).isContadinoLupo(); }

    private Optional<Entry<String, Giocatore>> cercaNosferatu()
    {
        return cercaGiocatore(ruolo -> ruolo.getValue().isNosferatu());
    }

    private Optional<Entry<String, Giocatore>> cercaContadinoMostro()
    {
        return cercaGiocatore(ruolo -> ruolo.getValue().isContadinoMostro());
    }

    private Optional<Entry<String, Giocatore>> cercaAngeloCustode()
    {
        return cercaGiocatore(ruolo -> ruolo.getValue().isAngeloCustode());
    }

    private Optional<Entry<String, Giocatore>> cercaGiocatore(Predicate<Entry<String, Giocatore>> predicato)
    {
        return getGiocatori().filter(predicato).findAny();
    }

    private void ordinaGiocatori(Comparator<Entry<String, Giocatore>> comparatore)
    {
        Map<String, Giocatore> copia =
            getGiocatori().sorted(comparatore).collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        giocatori.clear();
        for(String nome : copia.keySet()) giocatori.put(nome, copia.get(nome));
    }

    private Stream<Entry<String, Giocatore>> getGiocatori() { return giocatori.entrySet().stream(); }

    private void ordinaAlfabeticamente() { ordinaGiocatori(new ComparatoreAlfabetico()); }

    private Stream<Giocatore> getStreamRuoli() { return getRuoli().stream(); }

    private Collection<Giocatore> getRuoli() { return giocatori.values(); }

}