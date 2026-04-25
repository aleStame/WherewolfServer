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
          "di voti pari ai ruoli con fazione Città in gioco più uno."
        );
    }

    @Override public boolean isBorgomastro() { return true; }

    public static Ruolo getInstance() { return new Borgomastro(); }

}