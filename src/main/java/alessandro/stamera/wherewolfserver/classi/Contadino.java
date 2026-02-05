package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.VILLAGGIO;

public class Contadino extends Ruolo
{

    public Contadino(Aura aura)
    {
        super
        (
        "Contadino", VILLAGGIO, aura,
    "Il Contadino ha una delle seguenti identità nascoste (a sua insaputa) : Semplice, Eroe, Discendente dei Lupi, Mostro.", 1,
              false
        );
    }

    public void segnalazioneAzzeccagarbugli() { setAccusato(true); }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isContadino() { return true; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isAngeloCustode() { return false; }

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
