package alessandro.stamera.wherewolfserver.classi;

import java.util.LinkedHashMap;
import java.util.Map;
import static alessandro.stamera.wherewolfserver.classi.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.IstanzaRuolo.values;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_BRANCO;
import static alessandro.stamera.wherewolfserver.classi.Fazione.LUPO_SOLITARIO;

public final class RuoliFactory
{

    private final Map<String, Ruolo> ruoli;

    public RuoliFactory()
    {
        ruoli = new LinkedHashMap<>();
        for(IstanzaRuolo istanza : values())
        {
            Ruolo ruolo = istanza.getRuolo();
            ruoli.put(ruolo.getNome(), ruolo);
        }
        ruoli.get("Cappuccetto rosso").aggiungiProtezioneLupi();
        ruoli.get("Eremita").aggiungiProtezione(CREATURE_OMBRA);
        ruoli.get("Ladra").aggiungiProtezione(CREATURE_OMBRA);
        System.out.println("Protezioni caricate");
    }

    public Ruolo getRuolo(String nome) { return ruoli.get(nome); }

}