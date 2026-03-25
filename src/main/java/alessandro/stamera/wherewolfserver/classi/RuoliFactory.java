package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.values;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static java.util.Arrays.stream;

public final class RuoliFactory
{

    private final Map<String, Ruolo> ruoli;

    public RuoliFactory()
    {
        ruoli = new LinkedHashMap<>();
        caricaRuoli();
    }

    public Ruolo getRuolo(String nome)
    {
        Ruolo ruolo;
        switch(nome)
        {
            case "Cappuccetto rosso" -> ruolo = getCappuccettoRosso();
            case "Goblin" -> ruolo = getGoblin();
            case "Eremita" -> ruolo = getEremita();
            case "Ladra" -> ruolo = getLadra();
            case "Leprecauno" -> ruolo = getLeprecauno();
            default -> ruolo = ruoli.get(nome);
        }
        return ruolo;
    }

    public int getNumeroRuoli() { return ruoli.size(); }

    public String getNome(int posizione) { return ruoli.keySet().stream().toList().get(posizione); }

    public Ruolo[] getLupi() { return filtraRuoli(Ruolo::isLupo); }

    public Ruolo[] getMistici() { return filtraRuoli(Ruolo::isMistico); }

    public Ruolo[] getCreatureOmbra()
    {
        return filtraRuoli(ruolo -> ruolo.isTrattoPresente(CREATURA_OMBRA) || ruolo.getCategoria() == CREATURE_OMBRA);
    }

    private Ruolo getCappuccettoRosso()
    {
        Ruolo ruolo = ruoli.get("Cappuccetto rosso");
        ruolo.aggiungiProtezione(getLupi());
        return ruolo;
    }

    private Ruolo getGoblin() { return getPiccoloPopolo("Goblin", (ruolo -> !ruolo.isGoblin())); }

    private Ruolo getEremita() { return getPersonaggioProtetto("Eremita"); }

    private Ruolo getLadra() { return getPersonaggioProtetto("Ladra"); }

    private Ruolo getLeprecauno() { return getPiccoloPopolo("Leprecauno", (ruolo -> !ruolo.isLeprecauno())); }

    private Ruolo getPersonaggioProtetto(String nome)
    {
        Ruolo ruolo = ottieniRuolo(nome);
        ruolo.aggiungiProtezione(getCreatureOmbra());
        return ruolo;
    }

    private void caricaRuoli() { for(IstanzaRuolo istanza : values()) aggiungiRuolo(istanza); }

    private Ruolo getPiccoloPopolo(String nome, Predicate<Ruolo> condizione)
    {
        Ruolo ruolo = ottieniRuolo(nome);
        ruolo.aggiungiProtezione(toArray(stream(getMistici()).filter(condizione).filter(mistico -> !mistico.isMedium()).toList()));
        return ruolo;
    }

    private Ruolo[] filtraRuoli(Predicate<Ruolo> predicato) { return toArray(ruoli.values().stream().filter(predicato).toList()); }

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

    private Ruolo ottieniRuolo(String nome) { return ruoli.get(nome); }

}