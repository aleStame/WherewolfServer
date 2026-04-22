package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoPartita.NON_FINITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoPartita.VITTORIA;

public final class AngeloCustode extends Amanti
{

    private AngeloCustode()
    {
        super
        (
      "Angelo custode",
  "La prima notte indica un giocatore, l'Amato, che viene avvisato. Se quel giocatore dovesse essere accusato, l'Angelo custode sarà " +
            "accusato al suo posto. Se dovesse essere attaccato e ucciso durante la notte, sarà invece attaccato, avvisato e ucciso l'Angelo " +
            "custode."
        );
    }

    @Override public boolean isAmato() { return false; }

    @Override public boolean isAngeloCustode() { return true; }

    @Override public void sceltaAngeloCustode() { }

    @Override public EsitoPartita getEsitoPartita(Partita partita)
    {
        EsitoPartita esito = NON_FINITO;
        if(partita.isFinita()) if(partita.isGiuliettaViva()) esito = VITTORIA;
        return esito;
    }

    public static Ruolo getInstance() { return new AngeloCustode(); }

}