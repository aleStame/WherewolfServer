package alessandro.stamera.wherewolfserver.classi;

public class Guardia extends Villaggio
{

    public Guardia(String nome, Aura aura, String descrizione) { super(nome, aura, descrizione, 2, false); }

    @Override public boolean isGuardia() { return true; }

}