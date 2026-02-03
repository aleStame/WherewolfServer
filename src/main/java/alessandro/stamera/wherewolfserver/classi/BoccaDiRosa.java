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
        int risultato = getVotiDimezzati();
        if(isVotiDispari()) risultato++;
        return risultato;
    }

    @Override public boolean isBoccaDiRosa() { return !super.isBoccaDiRosa(); }

    @Override public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    private int getVotiDimezzati() { return getVotiInteri() / 2; }

    private boolean isVotiDispari() { return getVotiInteri() % 2 == 1; }

    private int getVotiInteri() { return super.getNumeroVoti(); }

}