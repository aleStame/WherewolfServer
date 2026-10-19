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
          "per eccesso.",
    "Come il Peccatore, siete un Ruolo dall’aura nera che gioca però per la vittoria degli Uomini. A differenza di lui, il vostro potere " +
            "vi rende abbastanza protetti nelle votazioni, ma attenti a non farci troppo affidamento perché, se in troppi dovessero convincersi " +
            "che siete un Lupo, potreste rischiare di finire ugualmente al rogo. Essendo un ruolo con fazione Città, diversi altri giocatori con " +
            "la stessa fazione potrebbero beneficiare della vostra presenza e usare i loro poteri per proteggervi. Questo potrebbe essere un " +
            "motivo in più per rivelarvi apertamente."
        );
    }

    @Override public boolean isBoccaDiRosa() { return true; }

    /*@Override public int getNumeroVoti()
    {
        int risultato = getVotiDimezzati();
        if(isVotiDispari()) risultato++;
        return risultato;
    }*/

    public static Ruolo getInstance() { return new BoccaDiRosa(); }

    private int getVotiDimezzati() { return getVotiInteri() / 2; }

    private boolean isVotiDispari() { return getVotiInteri() % 2 == 1; }

    private int getVotiInteri()
    {
        //return super.getNumeroVoti();
        return 0;
    }

}