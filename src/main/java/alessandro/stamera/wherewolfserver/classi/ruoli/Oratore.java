package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Oratore extends Citta
{

    private Oratore()
    {
        super
        (
    "Oratore", BIANCA,
"Può votare al ballottaggio anche se è accusato. Se un altro giocatore con fazione Città dovesse andare al rogo, il rogo viene " +
          "annullato. Se non è accusato, può segnalare uno o più giocatori durante il ballottaggio: se uno di quei giocatori dovesse andare al " +
          "rogo, il rogo viene annullato",
    "Avete un grande potere, ma anche molto rischioso e delicato da usare. Ricordatevi che annullare un rogo può voler dire anche salvare " +
            "un vostro nemico, usate quindi il vostro potere solo quando vi fidate davvero della persona che state proteggendo. Se usate il " +
            "vostro potere indiscriminatamente non farete altro che giocare per i Lupi e le altre Creature dell'Ombra. in quanto più roghi " +
            "verranno annullati, più notti avranno loro per mietere le loro vittime."
        );
    }

    @Override public boolean isOratore() { return true; }

    public static Ruolo getInstance() { return new Oratore(); }

}