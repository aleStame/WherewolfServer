package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

public enum Aura
{

    BIANCA("Bianca"), NERA("Nera");

    private final String descrizione;

    Aura(String descrizione){ this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static Aura getAura(String descrizione)
    {
        Aura risultato = BIANCA;
        if(descrizione.equals(NERA.toString())) risultato = NERA;
        return risultato;
    }

}