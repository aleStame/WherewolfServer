package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.AMANTI;

public final class AngeloCustode extends Ruolo
{

    public AngeloCustode()
    {
        super
        (
      "Angelo custode", AMANTI, BIANCA,
  "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode.",
        2, false
        );
    }

    public void segnalazioneAzzeccagarbugli() { setAccusato(true); }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isAngeloCustode() { return true; }

    public boolean isAssassino() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return false; }

    public boolean isCapoGilda() { return false; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isBardo() { return false; }

    public boolean isLupo() { return false; }

}