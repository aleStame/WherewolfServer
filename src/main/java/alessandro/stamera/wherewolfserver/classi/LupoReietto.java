package alessandro.stamera.wherewolfserver.classi;

public final class LupoReietto extends Lupo
{

    public LupoReietto() { super("Lupo reietto", null, 3); }

    @Override public boolean isLupoReietto() { return !super.isLupoReietto(); }

}