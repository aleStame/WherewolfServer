package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

public final class Peccatore extends Villaggio
{

    private Peccatore()
    {
        super
        (
    "Peccatore", NERA,
"La prima notte viene individuato dal Prete e apre gli occhi nel turno del Posseduto. Se rimane in gioco, vince con una vittoria degli " +
          "uomini, altrimenti con il Posseduto",
     1, false,
    "Il vostro è un ruolo molto difficile. Il vostro obiettivo è lo stesso di tutti gli altri abitanti del villaggio, ma in caso veniate " +
            "sondati dalla Veggente, vedrà un'aura nera. L'unico vostro alleato per non essere scambiato per un Lupo e finire quindi sul rogo è " +
            "il Prete, che conosce la vostra identità. Ricordate tuttavia che voi non conoscete la sua, quindi pensateci bene prima di fidarvi " +
            "di un Prete solo perché vi sta scagionando. Se davvero il villaggio non si fida per voi, ricordatevi che in questo gioco non si " +
            "deve rimanere in vita per vincere: essere pronti ad andare sul rogo, a volte, è il modo migliore per distinguersi da un Lupo. " +
            "Sapete chi è il Posseduto. Che sia lui a vincere, oppure il villaggio, per voi non cambia. Se da una parte questo vi rende ancora " +
            "meno affidabili per il villaggio, dall'altra vi da una condizione di vittoria ulteriore che procede nell'ombra."
        );
    }

    @Override public boolean isPeccatore() { return true; }

    public static Ruolo getInstance() { return new Peccatore(); }

}