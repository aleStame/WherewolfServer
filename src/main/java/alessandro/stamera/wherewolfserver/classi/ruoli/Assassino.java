package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

public final class Assassino extends Criminale
{

    private Assassino()
    {
        super
        (
    "Assassino", NERA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.",
   "Avete un solo colpo da sparare e dovete decidere molto bene come usarlo. L'obiettivo migliore potrebbe essere una Guardia, poiché " +
           "nessun altro, a parte i Criminali, ha interesse che le guardie vengano eliminate. Di sicuro anche un Lupo sarebbe un ottimo " +
           "bersaglio, anche perché, se capisse chi siete, sarebbe forse lui ad eliminare voi. Scegliete bene il momento in cui aprire gli occhi " +
           "perché, se il Mistico di turno dovesse essere in gioco, dovreste uccidere per forza lui e perdereste così la vostra unica " +
           "occasione."
        );
    }

    @Override public boolean isAssassino() { return true; }

    public static Ruolo getInstance() { return new Assassino(); }

}
