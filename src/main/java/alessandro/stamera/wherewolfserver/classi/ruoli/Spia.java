package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Spia extends Criminale
{

    private Spia()
    {
        super
        (
    "Spia", BIANCA,
"La prima notte riconosce gli altri criminali. Può tenere gli occhi aperti durante le votazioni per le accuse. Se lo fa, in quella" +
          " votazione può votare per sé stesso",
    "Le informazioni in vostro possesso saranno veramente tantissime. Dal modo in cui vota un giocatore si può capire davvero molto su " +
            "di lui, inoltre conoscere immediatamente l'identità del Borgomastro, del Mercante, dell'Azzeccagarbugli non appena useranno i loro " +
            "poteri. Il vostro scopo è quello di usare al meglio queste informazioni senza rivelare la vostra identità. Siete uno dei Criminali " +
            "con aura bianca, cercate di sfruttare al massimo questa caratteristica e di far sapere ai vostri alleati quello che avete capito " +
            "senza dare troppo nell'occhio."
        );
    }

    @Override public boolean isSpia() { return true; }

    public static Ruolo getInstance() { return new Spia(); }

}