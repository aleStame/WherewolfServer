package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public final class Spia extends Criminale
{

    private Spia()
    {
        super
        (
    "Spia", BIANCA,
"La prima notte riconosce gli altri criminali. Può tenere gli occhi aperti durante le votazioni per le accuse. Se lo fa, in quella" +
          " votazione può votare per sé stesso"
        );
    }

    @Override public boolean isSpia() { return true; }

    public static Ruolo getInstance() { return new Spia(); }

}