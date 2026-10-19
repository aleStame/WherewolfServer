package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;

public final class Azzeccagarbugli extends Citta
{

    private Azzeccagarbugli()
    {
        super
        (
    "Azzeccagarbugli", BIANCA,
"Può votare al ballottaggio anche se è accusato e può segnalare un altro giocatore durante le accuse: se la sua fazione è Città o " +
          "Criminali, i voti che riceve vengono azzerati, altrimenti sarà accusato a prescindere dai voti ricevuti.",
   "Il vostro ruolo ha due effetti opposti: proteggere i vostri alleati della Città e costringere ad una arringa di difesa quelli che " +
           "non godono della vostra fiducia. Durante i primi turni questo secondo aspetto è sicuramente molto importante perché farà in modo " +
           "che ci siano più giocatori esposti e costretti a rivelare informazioni. Se il giocatore che segnalate non dovesse essere accusato, " +
           "allora saprete di aver trovato un alleato, starà poi a voi fargli capire, magari velatamente, che siete dalla stessa parte."
        );
    }

    @Override public boolean isAzzeccagarbugli() { return true; }

    public static Ruolo getInstance() { return new Azzeccagarbugli(); }

}