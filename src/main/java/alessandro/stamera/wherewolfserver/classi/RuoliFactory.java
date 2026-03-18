package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.values;

public final class RuoliFactory
{

    private final Map<String, Ruolo> ruoli;

    public RuoliFactory()
    {
        ruoli = new LinkedHashMap<>();
        caricaRuoli();
        aggiungiProtezioneCreatureOmbra("Eremita", "Ladra");
        List<Ruolo> mistici = ruoli.values().stream().filter(ruolo -> ruolo.isMistico() && !ruolo.isGoblin()).toList();
        Ruolo[] temp = new Ruolo[mistici.size()];
        mistici.toArray(temp);
        getRuolo("Goblin").aggiungiProtezione(temp);
    }

    public Ruolo getCappuccettoRosso()
    {
        Ruolo ruolo = getRuolo("Cappuccetto rosso");
        ruolo.aggiungiProtezioneLupi();
        return ruolo;
    }

    public Ruolo getRuolo(String nome) { return ruoli.get(nome); }

    public int getNumeroRuoli() { return ruoli.size(); }

    public String getNome(int posizione) { return ruoli.keySet().stream().toList().get(posizione); }

    public Ruolo[] getLupi() { return filtraRuoli(Ruolo::isLupo); }

    public Ruolo[] getMistici() { return filtraRuoli(Ruolo::isMistico); }

    private void caricaRuoli() { for(IstanzaRuolo istanza : values()) aggiungiRuolo(istanza); }

    private Ruolo[] filtraRuoli(Predicate<Ruolo> predicato) { return toArray(ruoli.values().stream().filter(predicato).toList()); }

    private void aggiungiProtezioneCreatureOmbra(String... nomi)
    {
        for(String nome : nomi) getRuolo(nome).aggiungiProtezione(CREATURE_OMBRA);
    }

    private void aggiungiRuolo(IstanzaRuolo istanza)
    {
        Ruolo ruolo = istanza.getRuolo();
        ruoli.put(getNome(ruolo), ruolo);
    }

    private String getNome(Ruolo ruolo)
    {
        String nome;
        if(ruolo.isContadino()) nome = getNomeContadino(ruolo);
        else nome = ruolo.getNome();
        return nome;
    }

    private String getNomeContadino(Ruolo ruolo)
    {
        String nome;
        if(ruolo.isContadinoNormale()) nome = "Contadino normale";
        else if(ruolo.isContadinoEroe()) nome = "Contadino eroe";
        else if(ruolo.isContadinoMostro()) nome = "Contadino mostro";
        else nome = "Contadino discendente dei lupi";
        return nome;
    }

    private Ruolo[] toArray(List<Ruolo> lista)
    {
        Ruolo[] array = new Ruolo[lista.size()];
        lista.toArray(array);
        return array;
    }

}