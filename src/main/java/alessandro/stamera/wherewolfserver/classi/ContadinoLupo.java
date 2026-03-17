package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.LUPO_MANNARO;

public final class ContadinoLupo extends Contadino
{

    private ContadinoLupo() { super(); }

    @Override public boolean isContadinoLupo() { return true; }

    @Override public boolean attacco(Ruolo ruolo)
    {
        boolean esito = super.attacco(ruolo);
        if(esito && ruolo.isLupo())
        {
            aggiungiTrattiOscuri();
            esito = false;
        }
        return esito;
    }

    public static Ruolo getInstance() { return new ContadinoLupo(); }

    private void aggiungiTrattiOscuri() { aggiungiTratti(CREATURA_OMBRA, LUPO_MANNARO); }

}
