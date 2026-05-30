package alessandro.stamera.wherewolfserver.classi.attributi_ruolo;

import static java.util.Arrays.stream;

public enum Fazione
{

    NESSUNA(), LUPO_BRANCO("Lupi del branco"), LUPO_SOLITARIO("Lupo solitario"), VAMPIRO("Vampiro"),
    NOSFERATU("Nosferatu"), NEGROMANTE("Negromante"), POSSEDUTO("Posseduto"),
    VILLAGGIO("Villaggio"), CITTA("Città"), CRIMINALI("Criminali"), AMANTI("Amanti"),
    INQUISIZIONE("Inquisizione");

    private final String descrizione;

    Fazione() { this("-"); }

    Fazione(String descrizione) { this.descrizione = descrizione; }

    @Override public String toString() { return descrizione; }

    public static Fazione getFazione(String descrizione)
    {
        return stream(values()).filter(fazione -> fazione.toString().equals(descrizione)).findFirst().get();
    }

}