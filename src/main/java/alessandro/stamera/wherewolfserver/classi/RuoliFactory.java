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
        for(IstanzaRuolo istanza : values())
        {
            Ruolo ruolo = istanza.getRuolo();
            ruoli.put(getNome(ruolo), ruolo);
        }
        getRuolo("Cappuccetto rosso").aggiungiProtezioneLupi();
        getRuolo("Eremita").aggiungiProtezione(CREATURE_OMBRA);
        getRuolo("Ladra").aggiungiProtezione(CREATURE_OMBRA);
    }

    public Ruolo getRuolo(String nome) { return ruoli.get(nome); }

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