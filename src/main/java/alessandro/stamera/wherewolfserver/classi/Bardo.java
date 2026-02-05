package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public final class Bardo extends Ruolo
{

    public Bardo()
    {
        super
        (
    "Bardo", VILLAGGIO, BIANCA,
"Ogni mattina, se la Veggente quella notte ha scoperto un'aura bianca, il Moderatore la comunica pubblicamente.", 1, false
        );
    }

    @Override public boolean isBardo() { return true; }

    public void segnalazioneAzzeccagarbugli() { setAccusato(true); }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return false; }

    public boolean isCapoGilda() { return false; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isLupo() { return false; }

}
