package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.BIANCA;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestAltraGuardia
{

    private static final String NOME = "Altra guardia";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione = "La prima notte riconosce le altre guardie, poi scopre dal moderatore il numero di criminali presenti in gioco.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isFalse(); }

    @Test public void testControlloMedium() { verificaAuraBianca(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Se non vi sono Criminali, avrete una partita più tranquilla: avere qualcuno che conosce il vostro Ruolo è sicuramente molto utile " +
            "durante le Votazioni e le difese. Attenzione solo a non essere scambiati per i due Lupi Mannari! Il Ruolo delle Guardie diventa " +
            "molto più interessante, e delicato, qualora vi siano in gioco dei Criminali. In questo caso, la sopravvivenza di una delle due " +
            "Guardie diventa di vitale importanza per la sicurezza del villaggio. Cercate di non rivelarvi subito: più rimarrete nascosti " +
            "maggiori saranno le possibilità di salvarvi dall’Assassino e di catturare il Capo Gilda. Rivelarsi Guardia può essere pericoloso " +
            "anche se si pensa che possa essere presente il Vampiro. Se dovesse infatti trasformare una delle Guardie, avrebbe modo di sapere " +
            "facilmente chi sono anche le altre.";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaAuraBianca(Aura aura) { assertThat(aura).isEqualTo(BIANCA); }

}