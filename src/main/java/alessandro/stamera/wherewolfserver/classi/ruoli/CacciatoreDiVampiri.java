package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;

import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.MORTO;

public final class CacciatoreDiVampiri extends Villaggio
{

    private CacciatoreDiVampiri()
    {
        super
        (
    "Cacciatore di vampiri", BIANCA,
"La prima notte scopre il Vampiro è in gioco. È protetto dal Vampiro e, se viene attaccato, viene avvisato e lo elimina", 2,
    false
        );
    }

    @Override public boolean isCacciatoreDiVampiri() { return true; }

    @Override public EsitoAttacco attaccoNosferatu() { return MORTO; }

    @Override public EsitoAttacco vampirizzazione() { return getMorto(); }

    private EsitoAttacco getMorto() { return MORTO; }

    public static Ruolo getInstance() { return new CacciatoreDiVampiri(); }

}