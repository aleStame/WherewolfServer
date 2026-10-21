package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public final class LupoReietto extends Lupo
{

    private LupoReietto()
    {
        super
        (
    "Lupo reietto",
"La prima notte individua il Traditore e la Megera e riconosce i lupi del branco. Dalla prima notte, se è il lupo più potente in " +
          "gioco, durante il turno dei lupi, può indicare un giocatore che viene ucciso. É protetto dal Capo branco e perde la partita se questi " +
          "alla fine è ancora in gioco.",
     3,
    "Il vostro è un obiettivo difficile. Dovrete evitare il rogo e guadagnarvi la fiducia del villaggio e, allo stesso tempo, fare in " +
            "modo che il Capo Branco sia eliminato prima della fine della partita. Per fare questo dovrete ricorrere ad una strategia indiretta, " +
            "magari appoggiandolo solo per tradirlo quando meno se lo aspetta, la vostra sopravvivenza non è indispensabile per la vostra " +
            "vittoria. Ricordate anche che questa è una vostra sfida personale, gli altri Lupi del Branco non avranno alcun interesse nel suo " +
            "esito. Vincere la sfida contro il villaggio e quella contro il Capo Branco potrebbe rivelarsi arduo, ma di certo molto soddisfacente " +
            "qualora vi riusciste."
        );
    }

    @Override public boolean isLupoReietto() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo) { return FALLITO; }

    public static Ruolo getInstance() { return new LupoReietto(); }

}