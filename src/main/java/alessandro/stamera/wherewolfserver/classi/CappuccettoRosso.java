package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

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

    public static Ruolo getInstance() { return new CappuccettoRosso(); }

}
