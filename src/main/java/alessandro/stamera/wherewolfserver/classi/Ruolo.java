package alessandro.stamera.wherewolfserver.classi;

public record Ruolo(String nome, Fazione fazione, Aura aura, String descrizione, int lune)
{

    public Categoria getCategoria() { return fazione.getCategoria(); }

}