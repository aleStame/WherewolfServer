package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.CreatureOmbra;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VAMPIRO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;

public final class Vampiro extends CreatureOmbra
{

    private Vampiro()
    {
        super
        (
    "Vampiro", VAMPIRO, NERA,
"La prima notte riconosce il Ghoul. Dalla seconda notte, può indicare un giocatore che viene avvisato. Se è il Cacciatore di vampiri o " +
          "un lupo, il Vampiro viene ucciso. Se è un mistico, non accade nulla. Altrimenti, quel giocatore lo riconosce e diventa una progenie " +
          "vampirica con aura oscura e fazione Vampiro. Inoltre, la prima notte individua la Megera.",
     2, false,
    "Di notte non siete potenti quanto i Lupi e dovete temere i loro attacchi; di giorno, non siete influenti quanto il villaggio e " +
            "dovete temere il rogo. I vostri poteri vi rendono comunque temibile in entrambi i momenti del gioco. Scegliete bene le vittime, " +
            "aspettando anche un turno in più, se necessario, e non cercate di attaccare ogni turno. Scoprirete che, se ben gestiti, anche solo " +
            "un paio di Non Morti sono sufficienti per assicurarsi la vittoria, anche qualora voi doveste essere uccisi nel frattempo. Ricordate " +
            "che i Non Morti non si conoscono fra di loro. Sarete voi, scegliendo accuratamente le parole, a far loro capire chi devono votare " +
            "e di chi devono fidarsi."
        );
        aggiungiTratti(CREATURA_OMBRA);
    }

    public boolean isVampiro() { return true; }

    public static Ruolo getInstance() { return new Vampiro(); }

}