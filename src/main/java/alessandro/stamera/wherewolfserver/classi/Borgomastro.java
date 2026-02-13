package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;

public class Borgomastro extends Citta
{

    public Borgomastro()
    {
        super
        (
    "Borgomastro", BIANCA,
"Può votare al ballottaggio anche se è accusato. Può segnalare un giocatore durante il ballottaggio: quel giocatore riceve un minimo " +
          "di voti pari ai ruoli con fazione Città in gioco più uno."
        );
    }

    @Override public boolean isBorgomastro() { return true; }

}
