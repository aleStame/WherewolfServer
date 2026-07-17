package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.ANGELO_CUSTODE_MORTO;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.NONNA_BECCATA;

public final class Nonna extends Villaggio
{

    private Nonna()
    {
        super
        (
    "Nonna", BIANCA,
"Non ci sono più creature dell'ombra ed almeno un giocatore è in gioco. Non ci sono Criminali in gioco a fine partita o, se almeno un " +
          "Criminale è ancora in gioco, lo è anche almeno uno tra Guardia e Altra guardia",
     1, false
        );
    }

    @Override public boolean isNonna() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo ruolo)
    {
        EsitoAttacco esito = super.attaccoLupi(ruolo);
        if(esito != ANGELO_CUSTODE_MORTO) esito = NONNA_BECCATA;
        return esito;
    }

    public static Ruolo getInstance() { return new Nonna(); }

}