package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;

public final class Prete extends Villaggio
{

    private Prete()
    {
        super
        (
    "Prete", BIANCA,
"La prima notte individua il Peccatore e scopre se Bocca di rosa e il Posseduto sono in gioco. È protetto dal Posseduto e, se viene " +
          "attaccato, viene avvisato e gli fa perdere tutti i poteri.",
     1, false
        );
    }

    @Override public boolean isPrete() { return true; }

    @Override public EsitoAttacco passaPosseduto() { return MORTO; }

    public static Ruolo getInstance() { return new Prete(); }

}