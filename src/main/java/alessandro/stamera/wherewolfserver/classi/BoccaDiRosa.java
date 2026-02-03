package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CITTA;

public final class BoccaDiRosa extends Ruolo
{

    public BoccaDiRosa()
    {
        super
        (
    "Bocca di rosa", CITTA, NERA,
"Può votare al ballottaggio anche se è accusata. In ogni votazione i voti che Bocca di rosa riceve vengono dimezzati, arrotondando " +
          "per eccesso.",
     2, false
        );
    }

    @Override public int getNumeroVoti()
    {
        int risultato = super.getNumeroVoti() / 2;
        if(isVotiDispari()) risultato++;
        return risultato;
    }

    @Override public boolean isBoccaDiRosa() { return !super.isBoccaDiRosa(); }

    private boolean isVotiDispari() { return super.getNumeroVoti() % 2 == 1; }

}