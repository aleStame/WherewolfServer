package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public final class Assassino extends Ruolo
{

    public Assassino()
    {
        super
        (
    "Assassino", CRIMINALI, BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte, può aprire gli occhi nel turno di un " +
          "mistico. Se quel mistico in gioco, viene ucciso. Altrimenti, l'Assassino indica un giocatore che viene avvisato ed ucciso.",
     2, false
        );
    }

    @Override public boolean isAssassino() { return true; }

    public void segnalazioneAzzeccagarbugli() { annullaVoti(); }

    public boolean isContadino() { return false; }

    public boolean isContadinoNormale() { return false; }

    public boolean isContadinoMostro() { return false; }

    public boolean isContadinoEroe() { return false; }

    public boolean isContadinoLupo() { return false; }

    public boolean isCapoGilda() { return false; }

    public boolean isCapoBranco() { return false; }

    public boolean isLupoBranco() { return false; }

    public boolean isGiovaneLupo() { return false; }

    public boolean isLupoReietto() { return false; }

    public boolean isLupoSolitario() { return false; }

    public boolean isLupo() { return false; }

}
