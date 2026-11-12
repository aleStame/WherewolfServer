package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public class Borgomastro extends Citta
{

    private Borgomastro()
    {
        super
        (
    "Borgomastro", BIANCA,
"Può votare al ballottaggio anche se è accusato. Può segnalare un giocatore durante il ballottaggio: quel giocatore riceve un minimo " +
          "di voti pari ai ruoli con fazione Città in gioco più uno.",
    "A meno di trovarvi in un villaggio con diversi Ruoli con Fazione Città, il vostro potere sarà decisivo soprattutto nelle fasi " +
            "finali del gioco. Se riusciste ad arrivare ad un punto con pochi giocatori ancora presenti, allora sì che il vostro contributo " +
            "sarebbe davvero determinante. Pur non essendo un Mistico, il vostro Ruolo risulta particolarmente fastidioso per i Lupi Mannari, " +
            "quindi pensateci bene prima di rivelarvi, soprattutto se sapete di non essere l’unico Ruolo con Fazione Città."
        );
    }

    @Override public boolean isBorgomastro() { return true; }

    public static Ruolo getInstance() { return new Borgomastro(); }

}