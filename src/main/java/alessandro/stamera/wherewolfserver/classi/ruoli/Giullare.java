package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.EsitoAttacco.FALLITO;

public final class Giullare extends Ruolo
{

    private Giullare()
    {
        super
        (
            "Giullare", BIANCA,
            "Se viene messo al rogo, il giorno successivo il rogo sarà annullato a prescindere dai voti e dai poteri utilizzati. " +
            "Vince se viene messo al rogo.",
            1, false,
    "Di tutti i ruoli del gioco, il Giullare è sicuramente uno dei più anomali. Mentre gli altri ruoli, sia che giochino per gli Uomini " +
            "sia che giochino per l'Ombra, cercheranno di conquistarsi la fiducia degli altri giocatori, voi dovrete fare esattamente l'opposto! " +
            "Non potrete dire di essere una Creatura dell'Ombra per essere mandati al rogo, perché nessuna di loro lo farebbe mai. Il modo " +
            "migliore è sicuramente quello di difendere la vostra posizione, ma in maniera poco credibile cercando di destare sospetti ma sempre " +
            "senza darlo a vedere. Nel fare questo, cercate di non sembrare un bersaglio troppo interessante per i Lupi perché, se saranno loro " +
            "ad uccidervi, avrete perso la partita. Ricordate anche che, in ogni votazione, avrete sempre un voto sicuro per essere accusato, il " +
            "vostro!"
        );
    }

    @Override public boolean isGiullare() { return true; }

    @Override public EsitoAttacco gildata() { return FALLITO; }

    public static Ruolo getInstance() { return new Giullare(); }

}