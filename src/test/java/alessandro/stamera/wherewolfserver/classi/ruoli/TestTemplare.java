package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestTemplare
{

    private static final String NOME = "Templare";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { assertThat(ruolo.getNome()).isEqualTo(NOME); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testInquisizione() { verificaVero(ruolo.isInquisizione()); }

    @Test public void testBoia() { verificaFalso(ruolo.isBoia()); }

    @Test public void testInquisitore() { verificaFalso(ruolo.isInquisitore()); }

    @Test public void testTemplare() { verificaVero(ruolo.isTemplare()); }

    @Test public void testConsigliGiooco()
    {
        String messaggio =
            "La Strega è l'unico ruolo a poter fermare ogni notte l'attacco delle Creature dell'Ombra, a patto che riesca ad intuire le loro " +
            "mosse e ad agire preventivamente. Cercate di non rivelare la vostra identità se non assolutamente costretti, in quanto siete forse " +
            "l'unico bersaglio nella lista dei Lupi che viene ancora prima della Veggente. Dal momento che il vostro potere protegge sia " +
            "dall'attacco dei Lupi che da quello del Vampiro, ogni turno dovete decidere qual è la priorità maggiore perché i loro bersagli " +
            "saranno diversi e voi potete proteggere un giocatore soltanto. Capire se un giocatore è stato maledetto può risultare determinante " +
            "per leggere correttamente la partita. E' possibile infatti che la Veggente abbia visto un'aura nera su un giocatore che sostiene di " +
            "non possederla e che entrambi stiano dicendo il vero. Soltanto l'uso del vostro potere permetterà di discernere una situazione come " +
            "questa.";
        assertThat(ruolo.getConsigli()).isEqualTo(messaggio);
    }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}