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

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isAngeloCustode() { return false; }

    public boolean isAssassino() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return true; }

    public boolean isCapoGilda() { return false; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isBardo() { return false; }

    public boolean isLupo() { return false; }

    @Override public int getNumeroVoti()
    {
        int risultato = getVotiDimezzati();
        if(isVotiDispari()) risultato++;
        return risultato;
    }

    public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    private int getVotiDimezzati() { return getVotiInteri() / 2; }

    private boolean isVotiDispari() { return getVotiInteri() % 2 == 1; }

    private int getVotiInteri() { return super.getNumeroVoti(); }

}