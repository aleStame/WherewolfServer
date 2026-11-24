package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita;
import alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.TROVATO_POSSEDUTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoPartita.*;
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
     3, false,
    "Quello del Posseduto è un ruolo dell’Ombra diverso da tutti gli altri. Se siete il primo dei giocatori ad avere il ruolo, la vostra " +
            "posizione è la più delicata di tutte, in quanto non avete una seconda identità a vostro supporto. Il vostro scopo è quello di essere " +
            "uccisi dai Lupi il più in fretta possibile, per poter passare il Ruolo al giocatore che sembra godere maggiormente della fiducia del " +
            "villaggio."
        );
        aggiungiTratti(CREATURA_OMBRA);
    }

    @Override public boolean isPosseduto() { return true; }

    @Override public EsitoAttacco vampirizzazione() { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoNosferatu() { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo) { return getEsitoAttaccoDefault(); }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = super.getEsitoPartita(partita);
        if(esito != SCONFITTA)
        {
            if(partita.getNumeroGiocatoriVivi() == 2)
            {
                if(partita.isPossedutoPresente() && partita.getNumeroSenzaFazioneVivi() == 1) esito = VITTORIA;
                else esito = SCONFITTA;
            }
            else esito = NON_FINITO;
            System.out.println(esito);
        }
        return esito;
    }

    public static Ruolo getInstance() { return new Posseduto(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return TROVATO_POSSEDUTO; }

}