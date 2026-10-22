package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Mercante extends Citta
{

    public Mercante()
    {
        super
        (
    "Mercante", BIANCA,
"Può votare al ballottaggio anche se è accusato. In ogni votazione, non ha limite sul numero di giocatori per cui può votare. I voti " +
          "che il Mercante riceve vengono ridotti di uno per ogni altro giocatore della fazione Città in gioco.",
    "Da soli, avete altrettanti voti di tutto il resto del villaggio, chiaramente non più di uno per persona. Pensate bene quante volte " +
            "votare, poiché se voterete per tutti sarà come se non aveste votato per nessuno. Il vostro obiettivo, durante i primi turni, dovrebbe " +
            "essere quello di cercare di accusare il maggior numero di giocatori possibili, per aumentare il numero di informazioni in gioco. Il " +
            "vostro potere risulta particolarmente efficace quando il numero dei giocatori diminuisce, perché un singolo voto in più può fare la " +
            "differenza. Per quanto possibile, cercate di capire se ci sono altri ruoli della Città, questo può essere utile per capire chi non " +
            "votare, ma anche per sapere quanto il vostro potere vi protegge durante le votazioni"
        );
    }

    @Override public boolean isMercante() { return true; }

    public static Ruolo getInstance() { return new Mercante(); }

}