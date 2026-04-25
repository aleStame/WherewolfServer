package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Citta;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;

public final class BoccaDiRosa extends Citta
{

    private BoccaDiRosa()
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

    public static Ruolo getInstance() { return new BoccaDiRosa(); }

    private int getVotiDimezzati() { return getVotiInteri() / 2; }

    private boolean isVotiDispari() { return getVotiInteri() % 2 == 1; }

    private int getVotiInteri() { return super.getNumeroVoti(); }

}