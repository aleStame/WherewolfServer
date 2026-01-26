package alessandro.stamera.wherewolfserver.classi;

public final class LupoBranco extends Lupo
{

    public LupoBranco() { super("Lupo del branco", null, 1); }

    @Override public boolean isLupoBranco() { return !super.isLupoBranco(); }

}