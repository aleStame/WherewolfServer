package alessandro.stamera.wherewolfserver.classi;

public enum IstanzaRuolo
{

    ALTRA_GUARDIA(AltraGuardia.getInstance()), ANGELO_CUSTODE(AngeloCustode.getInstance()), ASSASSINO(Assassino.getInstance()),
    AZZECCAGARBUGLI(Azzeccagarbugli.getInstance()), BARDO(Bardo.getInstance()), BECCHINO(Becchino.getInstance()),
    BOCCA_DI_ROSA(BoccaDiRosa.getInstance()), BOIA(Boia.getInstance()), BORGOMASTRO(Borgomastro.getInstance()),
    BRACCONIERE(Bracconiere.getInstance()), CACCIATORE(Cacciatore.getInstance()), CACCIATORE_DI_VAMPIRI(CacciatoreDiVampiri.getInstance()),
    CAPO_BRANCO(CapoBranco.getInstance()), CAPO_GILDA(CapoGilda.getInstance()), CAPPUCCETTO_ROSSO(CappuccettoRosso.getInstance()),
    CONTADINO_EROE(ContadinoEroe.getInstance()), CONTADINO_LUPO(ContadinoLupo.getInstance()),
    CONTADINO_MOSTRO(ContadinoMostro.getInstance()), CONTADINO_NORMALE(ContadinoNormale.getInstance()), EREMITA(Eremita.getInstance()),
    GHOUL(Ghoul.getInstance()), GIOVANE_LUPO(GiovaneLupo.getInstance()), GIULIETTA(Giulietta.getInstance()),
    GIULLARE(Giullare.getInstance()), GOBLIN(Goblin.getInstance()), GUARDIA(GuardiaPrincipale.getInstance()),
    GUARDIA_CORROTTA(GuardiaCorrotta.getInstance()), GUARITORE(Guaritore.getInstance()), INQUISITORE(Inquisitore.getInstance()),
    LADRA(Ladra.getInstance()), LEPRECAUNO(Leprecauno.getInstance()), LUPO_BRANCO(LupoBranco.getInstance()),
    LUPO_REIETTO(LupoReietto.getInstance()), LUPO_SOLITARIO(LupoSolitario.getInstance()), MAGO(Mago.getInstance()),
    MEDIUM(Medium.getInstance()), MEGERA(Megera.getInstance()), MERCANTE(Mercante.getInstance()), MONACO(Monaco.getInstance()),
    NEGROMANTE(Negromante.getInstance()), NOSFERATU(Nosferatu.getInstance());

    private final Ruolo ruolo;

    IstanzaRuolo(Ruolo ruolo) { this.ruolo = ruolo; }

    public Ruolo getRuolo() { return ruolo; }

}