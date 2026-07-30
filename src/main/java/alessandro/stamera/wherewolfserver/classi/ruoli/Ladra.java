package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Potere;
import alessandro.stamera.wherewolfserver.classi.fazioni.Criminale;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public final class Ladra extends Criminale
{

    private final Potere potere;

    private Ladra()
    {
        super
        (
    "Ladra", BIANCA,
"La prima notte riconosce gli altri criminali. Una volta per partita, dalla seconda notte può aprire gli occhi nel turno di un " +
          "mistico. La prima volta che viene attaccata, è protetta dalle creature dell'ombra."
        );
        potere = new Potere();
    }

    @Override public boolean isLadra() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco esito = super.attaccoLupi(ruolo);
        if(esito == FALLITO) utilizzaPotere();
        return esito;
    }

    @Override public boolean maledizione()
    {
        boolean esito = super.maledizione();
        if(!esito) utilizzaPotere();
        return esito;
    }

    @Override public void utilizzaPotere()
    {
        perdiProtezioni();
        potere.utilizzaPotere();
    }

    @Override public boolean isPotereUtilizzato() { return potere.isPotereUtilizzato(); }

    @Override public EsitoAttacco vampirizzazione()
    {
        EsitoAttacco esito = super.vampirizzazione();
        if(esito == FALLITO && isProtezioneVampiroPresente()) perdiProtezioni();
        return esito;
    }

    public static Ruolo getInstance() { return new Ladra(); }

}