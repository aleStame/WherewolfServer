package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.NERA;

public final class BoccaDiRosa extends Citta
{

    public BoccaDiRosa()
    {
        super
        (
    "Bocca di rosa", NERA,
"Può votare al ballottaggio anche se è accusata. In ogni votazione i voti che Bocca di rosa riceve vengono dimezzati, arrotondando " +
          "per eccesso."
        );
    }

    @Override public boolean isBoccaDiRosa() { return true; }

    @Override public int getNumeroVoti()
    {
        int risultato = getVotiDimezzati();
        if(isVotiDispari()) risultato++;
        return risultato;
    }

    private int getVotiDimezzati() { return getVotiInteri() / 2; }

    private boolean isVotiDispari() { return getVotiInteri() % 2 == 1; }

    private int getVotiInteri() { return super.getNumeroVoti(); }

}