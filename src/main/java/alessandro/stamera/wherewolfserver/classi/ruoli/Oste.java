package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Oste extends Villaggio
{

    private Oste()
    {
        super
        (
    "Oste", BIANCA,
"Ogni mattino se la Veggente quella notte ha scoperto un'aura oscura, il moderatore lo comunica pubblicamente", 1, false,
    "L'Oste è un ruolo interessante da giocare, in quanto consente di smentire le affermazioni di presunte Veggenti senza che nessuno " +
            "debba rivelarsi. Anzi, il fatto che l'Oste sia in gioco dovrebbe rimanere segreto il più a lungo possibile, per fare in modo che le " +
            "false Veggenti si tradiscano da sole. A differenza del Bardo, il vostro potere si manifesterà meno volte, ma quando lo farà tutti " +
            "sapranno che un potenziale nemico è stato individuato quella notte. Se la Veggente il giorno prima si fosse rivelata e avesse detto " +
            "chi avrebbe indagato, il suo responso si saprebbe anche se i Lupi Mannari l'avessero uccisa quella notte stessa."
        );
    }

    @Override public boolean isOste() { return true; }

    public static Ruolo getInstance() { return new Oste(); }

}