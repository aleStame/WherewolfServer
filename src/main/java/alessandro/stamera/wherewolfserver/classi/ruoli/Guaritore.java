package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Guaritore extends Villaggio
{

    private final Potere potere;

    private Guaritore()
    {
        super
        (
    "Guaritore", BIANCA,
"Ogni notte individua i giocatori uccisi e una volta per partita può indicarne uno per farlo tornare in vita.", 1, true,
    "Il Guaritore è l'unico ruolo che può far guadagnare una notte al Villaggio con una certezza assoluta. Tenete a mente che dovrete " +
            "usare il vostro potere prima di essere uccisi o, peggio ancora, di essere costretti a rivelarvi, perché a quel punto sareste voi " +
            "ad essere attaccati. Ma attenzione a chi salvate, ricordate che non tutti i giocatori che vengono uccisi di notte giocano per il " +
            "Villaggio! Siete l'unico in gioco a poter tenere monitorato l'avanzamento del morbo. Ricordatevi che l'Appestato non si rende " +
            "conto di quanti giocatori stia contagiando, sarete voi a doverlo fare uscire allo scoperto qualora la situazione diventi troppo " +
            "pericolosa per il Villaggio."
        );
        potere = new Potere();
    }

    @Override public boolean isGuaritore() { return true; }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public void utilizzaPotere() { potere.utilizzaPotere(); }

    public static Ruolo getInstance() { return new Guaritore(); }

}
