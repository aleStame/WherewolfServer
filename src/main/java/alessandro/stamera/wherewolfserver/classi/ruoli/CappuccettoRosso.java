package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.*;

public final class CappuccettoRosso extends Villaggio
{

    private CappuccettoRosso()
    {
        super
        (
    "Cappuccetto rosso", BIANCA,
"Finché la Nonna è in gioco (anche se essa riceve il tratto Non morto o diventa il Posseduto) e non si è trasformata in Lupo, " +
          "Cappuccetto rosso è protetta dall'attacco dei Lupi. Se l'ultimo Lupo in gioco (sia esso l'ultimo Lupo del Branco o il Lupo solitario) " +
          "attacca Cappuccetto rosso, quest'ultima apre gli occhi e lo riconosce, anche se fosse Romeo o protetta dalla Strega",
     1, false
        );
    }

    @Override public boolean isCappuccettoRosso() { return true; }

    @Override public EsitoAttacco vampirizzazione()
    {
        EsitoAttacco esito = super.vampirizzazione();
        if(esito == RIUSCITO) perdiProtezioni();
        return esito;
    }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo)
    {
        EsitoAttacco esito = super.attaccoLupi(lupo);
        if(lupo.isLupoSolitario()) esito = getEsitoAttaccoLupoSolitario(esito);
        return esito;
    }

    public static Ruolo getInstance() { return new CappuccettoRosso(); }

    private EsitoAttacco getEsitoAttaccoLupoSolitario(EsitoAttacco esito)
    {
        switch(esito)
        {
            case RIUSCITO -> esito = ULTIMO_LUPO_UCCIDE_CAPPUCCETTO_ROSSO;
            case ANGELO_CUSTODE_MORTO -> esito = ULTIMO_LUPO_SVEGLIA_CAPPUCCETTO_ROSSO;
        }
        return esito;
    }

}
