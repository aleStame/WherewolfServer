package alessandro.stamera.wherewolfserver.classi;

public class GiovaneLupo extends Lupo
{

    public GiovaneLupo() { super("Giovane lupo", null, 0); }

    @Override public boolean isGiovaneLupo() { return !super.isGiovaneLupo(); }

}