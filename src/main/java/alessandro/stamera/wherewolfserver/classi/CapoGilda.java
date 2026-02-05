package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public final class CapoGilda extends Ruolo
{

    public CapoGilda()
    {
        super
        (
    "Capo gilda", CRIMINALI, BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico non è in gioco, indica un giocatore. Se è una Guardia o un lupo mannaro, il Capo gilda viene ucciso. Se la " +
          "sua fazione è Città o Villaggio, riconosce il Capo gilda, altrimenti non accade nulla",
    2, false
        );
    }

    public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    public boolean isAzzeccagarbugli() { return false; }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isAngeloCustode() { return false; }

    public boolean isAssassino() { return false; }

    public boolean isBecchino() { return false; }

    public boolean isBoccaDiRosa() { return false; }

    public boolean isCapoGilda() { return true; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isBardo() { return false; }

    public boolean isLupo() { return false; }

}
