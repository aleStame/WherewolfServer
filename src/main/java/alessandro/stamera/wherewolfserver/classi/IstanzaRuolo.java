package alessandro.stamera.wherewolfserver.classi;

public enum IstanzaRuolo
{

    ALTRA_GUARDIA(AltraGuardia.getInstance()), ANGELO_CUSTODE(AngeloCustode.getInstance()), ASSASSINO(Assassino.getInstance()),
    AZZECCAGARBUGLI(Azzeccagarbugli.getInstance()), BARDO(Bardo.getInstance()), BECCHINO(Becchino.getInstance()),
    BOCCA_DI_ROSA(BoccaDiRosa.getInstance()), BOIA(Boia.getInstance()), BORGOMASTRO(Borgomastro.getInstance()),
    BRACCONIERE(Bracconiere.getInstance()), CACCIATORE(Cacciatore.getInstance()), CACCIATORE_DI_VAMPIRI(CacciatoreDiVampiri.getInstance()),
    CAPO_GILDA(CapoGilda.getInstance()), GIOVANE_LUPO(GiovaneLupo.getInstance()),
    LUPO_BRANCO(LupoBranco.getInstance()), LUPO_REIETTO(LupoReietto.getInstance()), LUPO_SOLITARIO(LupoSolitario.getInstance()),
    CAPPUCCETTO_ROSSO(CappuccettoRosso.getInstance()), CAPO_BRANCO(CapoBranco.getInstance()), CONTADINO_EROE(ContadinoEroe.getInstance()),
    CONTADINO_LUPO(ContadinoLupo.getInstance()), CONTADINO_MOSTRO(ContadinoMostro.getInstance()),
    CONTADINO_NORMALE(ContadinoNormale.getInstance()), EREMITA(Eremita.getInstance()), GHOUL(Ghoul.getInstance()),
    GIULIETTA(Giulietta.getInstance()), GIULLARE(Giullare.getInstance()), GUARITORE(Guaritore.getInstance()), GOBLIN(Goblin.getInstance()),
    LEPRECAUNO(Leprecauno.getInstance()), GUARDIA_CORROTTA(GuardiaCorrotta.getInstance()), GUARDIA(GuardiaPrincipale.getInstance()),
    INQUISITORE(Inquisitore.getInstance()), LADRA(Ladra.getInstance());

    private final Ruolo ruolo;

    IstanzaRuolo(Ruolo ruolo) { this.ruolo = ruolo; }

    public Ruolo getRuolo() { return ruolo; }

}