package alessandro.stamera.wherewolfserver.classi;

import static alessandro.stamera.wherewolfserver.classi.Fazione.CRIMINALI;

public final class GuardiaCorrotta extends Guardia
{

    public GuardiaCorrotta()
    {
        super("Guardia corrotta");
        cambiaFazione(CRIMINALI);
    }

}
