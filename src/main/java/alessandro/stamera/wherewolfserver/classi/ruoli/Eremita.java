package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.fazioni.Villaggio;
import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.EREMITA_BECCATO;

public final class Eremita extends Villaggio
{

    private Eremita()
    {
        super
        (
    "Eremita", BIANCA, "È protetto dalle creature dell'ombra", 1, false,
    "Essendo immuni all'attacco di tutte le Creature dell'Ombra, dovete cercare di guadagnarvi la fiducia del Villaggio, poichè l'unica " +
            "possibilità per voi di essere eliminati è quell di finire al rogo. Potrebbe non essere facile, poichè è vero che voi non morirete " +
            "mai di notte, ma questo è vero anche per i Lupi Mannari, e questo potrebbe risultare sospetto col tempo. Una strategia efficace " +
            "potrebbe essere anche quella di fingervi un ruolo importante per farvi attaccare dai Lupi Mannari o dal Vampiro e togliergli una " +
            "notte di azione. L'unico inconveniente di questa strategia è che, se quel ruolo dovesse essere in gioco, il giocatore potrebbe " +
            "uscire allo scoperto per cercare di smascherarvi facendo crollare il vostro piano e mettendo in pericolo lui stesso."
        );
    }

    @Override public boolean isEremita() { return true; }

    @Override public EsitoAttacco attaccoLupi(Ruolo lupo) { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoNegromante() { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco vampirizzazione() { return getEsitoAttaccoDefault(); }

    @Override public EsitoAttacco attaccoNosferatu() { return getEsitoAttaccoDefault(); }

    private EsitoAttacco getEsitoAttaccoDefault() { return EREMITA_BECCATO; }

    public static Ruolo getInstance() { return new Eremita(); }

}
