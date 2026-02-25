package alessandro.stamera.wherewolfserver.classi;

public final class AngeloCustode extends Amanti
{

    public AngeloCustode()
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

}