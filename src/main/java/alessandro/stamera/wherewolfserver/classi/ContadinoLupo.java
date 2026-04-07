package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.Tratto.LUPO_MANNARO;

public final class ContadinoLupo extends Contadino
{

    private ContadinoLupo() { super(); }

    @Override public boolean isContadinoLupo() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco esito = super.attaccoLupi(ruolo);
        if(esito == RIUSCITO) esito = attivazioneLupo();
        return esito;
    }

    public static Ruolo getInstance() { return new ContadinoLupo(); }

    private void aggiungiTrattiOscuri() { aggiungiTratti(CREATURA_OMBRA, LUPO_MANNARO); }

    private EsitoAttacco attivazioneLupo()
    {
        aggiungiTrattiOscuri();
        return FALLITO;
    }

}
