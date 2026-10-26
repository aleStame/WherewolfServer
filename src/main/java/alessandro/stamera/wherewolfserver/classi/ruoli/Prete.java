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
     1, false,
    "Il Peccatore e Bocca di Rosa sono due ruoli molto difficili da giocare, in quanto hanno aura nera ma giocano per il villaggio. " +
            "Entrambi sono allo stesso tempo coperture molto facili per gli altri giocatori con aura nera presenti. Il vostro compito è quello di " +
            "confermare o smentire le loro identità ed evitare al villaggio un rogo sbagliato.\nQuella del Posseduto è una minaccia terribile e " +
            "silenziosa. Se sapete che è in gioco, più passa il tempo più sarà difficile individuarlo, perché potrebbe essere passato ad un " +
            "giocatore che, nel frattempo, si è conquistato la fiducia del villaggio. Il Peccatore potrebbe rivelarsi un grande alleato. Lui sa " +
            "chi è il Posseduto e voi sapete chi è lui, dovete soltanto convincerlo a rivelarvi il nome del suo altro alleato, anche se potrebbe " +
            "essere difficile."
        );
    }

    @Override public boolean isPrete() { return true; }

    @Override public EsitoAttacco passaPosseduto() { return MORTO; }

    public static Ruolo getInstance() { return new Prete(); }

}