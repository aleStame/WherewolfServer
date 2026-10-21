package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Inquisizione;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Inquisitore extends Inquisizione
{

    private Inquisitore()
    {
        super
        (
    "Inquisitore", BIANCA,
"La prima notte individua il Boia e il Templare e scopre quanti mistici sono in gioco. Se viene indicato da un mistico, lo riconosce. " +
          "Può segnalare un giocatore durante le accuse: se è un mistico, sarà accusato a prescindere dai voti ricevuti.",
    "Siete il fulcro della Fazione dell’Inquisizione. Conoscete l’identità dei vostri alleati, avete informazioni sul numero dei Mistici " +
            "in gioco e avete più modi per scovarli. Purtroppo non avete nessun modo per assicurarvi che vengano messi al rogo. Quella " +
            "dell’Inquisizione è una Fazione molto delicata da essere giocata. Pur non essendo fra i nemici del villaggio, il vostro obiettivo " +
            "potrebbe non farvi godere di buona luce agli occhi, in quanto quelli che per voi sono nemici per il resto dei giocatori sono gli " +
            "alleati più preziosi. Molto della vostra partita dipenderà dall’eventuale presenza di Boia e Templare. Il primo, soprattutto, sarà " +
            "determinante qualora capisca chi siete ed esegua le sentenze che voi provvederete a predisporre. Uno dei vostri migliori alleati " +
            "potrebbe essere il Mago, a patto che non sappia chi siete."
        );
    }

    @Override public boolean isInquisitore() { return true; }

    public static Ruolo getInstance() { return new Inquisitore(); }

}