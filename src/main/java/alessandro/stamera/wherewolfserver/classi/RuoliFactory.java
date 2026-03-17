package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.values;

public final class RuoliFactory
{

    private final Map<String, Ruolo> ruoli;

    public RuoliFactory()
    {
        ruoli = new LinkedHashMap<>();
        caricaRuoli();
        getRuolo("Cappuccetto rosso").aggiungiProtezioneLupi();
        aggiungiProtezioneCreatureOmbra("Eremita", "Ladra");
    }

    public Ruolo getRuolo(String nome) { return ruoli.get(nome); }

    public int getNumeroRuoli() { return ruoli.size(); }

    public String getNome(int posizione) { return ruoli.keySet().stream().toList().get(posizione); }

    private void caricaRuoli() { for(IstanzaRuolo istanza : values()) aggiungiRuolo(istanza); }

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

}