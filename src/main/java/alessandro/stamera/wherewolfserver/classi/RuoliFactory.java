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
            String nome = ruolo.getNome();
            if(nome.equals("Contadino"))
            {
                if(ruolo.isContadinoNormale()) nome = "Contadino normale";
                else if(ruolo.isContadinoEroe()) nome = "Contadino eroe";
                else if(ruolo.isContadinoMostro()) nome = "Contadino mostro";
                else nome = "Contadino discendente dei lupi";
            }
            ruoli.put(nome, ruolo);
        }
        ruoli.get("Cappuccetto rosso").aggiungiProtezioneLupi();
        ruoli.get("Eremita").aggiungiProtezione(CREATURE_OMBRA);
        ruoli.get("Ladra").aggiungiProtezione(CREATURE_OMBRA);
        System.out.println("Protezioni caricate");
    }

    public Ruolo getRuolo(String nome) { return ruoli.get(nome); }

}