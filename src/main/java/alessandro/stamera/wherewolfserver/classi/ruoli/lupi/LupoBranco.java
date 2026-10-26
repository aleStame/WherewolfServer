package alessandro.stamera.wherewolfserver.classi.ruoli.lupi;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Lupo;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;

public final class LupoBranco extends Lupo
{

    private LupoBranco()
    {
        super
        (
    "Lupo del branco",
"La prima notte individua il Traditore e riconosce i lupi del branco. Dalla seconda notte può indicare un giocatore che verrà " +
          "ucciso se è il lupo più potente in gioco.",
     1,
    "Come il Capo Branco, siete nella cima delle priorità da eliminare per il villaggio. Il vostro gioco dipenderà molto da come si " +
            "muovono gli altri Lupi. Se tutti cercheranno di rimanere nascosti, allora sarà preferibile per voi esporvi e confondere il " +
            "villaggio. Se invece qualcun altro lo farà per primo, avrete la possibilità di passare inosservati e magari arrivare a fine partita. " +
            "Costruite una strategia comune con gli altri Lupi: non solo il villaggio, anche il branco vincerà se giocherà unito. A parte dover " +
            "nascondere la vostra vera identità e cercare di rimanere vivi, ricordatevi che dovete anche conquistare la fiducia del Capo Branco. " +
            "Egli potrebbe infatti dubitare della vostra lealtà al branco credendovi il Lupo Reietto."
        );
    }

    @Override public boolean isLupoBranco() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo)
    {
        EsitoAttacco esito = super.attaccoLupi(lupo);
        if(lupo.isCapoBranco()) esito = RIUSCITO;
        return esito;
    }

    public static Ruolo getInstance() { return new LupoBranco(); }

}