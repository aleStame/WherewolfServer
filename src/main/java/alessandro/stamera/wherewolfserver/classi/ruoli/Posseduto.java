package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.POSSEDUTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;

public final class Posseduto extends CreatureOmbra
{

    private Posseduto()
    {
        super
        (
    "Posseduto", POSSEDUTO, NERA,
"La prima notte individua la Megera e riconosce il Peccatore. Anche se è stato ucciso, non è considerato eliminato dal gioco finché " +
           "un altro giocatore non diventa il Posseduto. Se è stato ucciso, indica un giocatore che lo riconosce: il ruolo di quel giocatore " +
           "diventa il Posseduto. Se viene ucciso al rogo perde tutti i poteri.",
     3, false
        );
        aggiungiTratti(CREATURA_OMBRA);
    }

    @Override public boolean isPosseduto() { return true; }

    @Override public EsitoAttacco vampirizzazione() { return FALLITO; }

    public static Ruolo getInstance() { return new Posseduto(); }

}