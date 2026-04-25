package alessandro.stamera.wherewolfserver.classi.ruoli.contadini;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.RIUSCITO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.CREATURA_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Tratto.LUPO_MANNARO;

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
