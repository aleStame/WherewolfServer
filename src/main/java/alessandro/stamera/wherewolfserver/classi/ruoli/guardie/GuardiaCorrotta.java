package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

public final class GuardiaCorrotta extends Criminale
{

    private GuardiaCorrotta()
    {
        super
        (
    "Guardia corrotta", NERA, "La prima notte riconosce le altre guardie e in seguito gli altri criminali.",
    "La Guardia Corrotta è il doppiogiochista per definizione. E' una delle guardie, ma il suo scopo è quello di eliminare le eventuali " +
            "altre Guardie prima della fine della partita. Come tutti i Criminali gioca una partita nella partita, dovendo prestare attenzione ai " +
            "Lupi, ma allo stesso tempo cercando di mascherare la propria identità, sopratutto alle altre Guardie sue compagne. Qualora non foste " +
            "l'unico Criminale e ci siano altre Guardie, sarà per voi fondamentale farlo capire ai vostri compagni per salvaguardarli, ma " +
            "sopratutto per dare un bersaglio ad un eventuale Assassino"
        );
    }

    @Override public boolean isGuardiaCorrotta() { return true; }

    public static Ruolo getInstance() { return new GuardiaCorrotta(); }

}
