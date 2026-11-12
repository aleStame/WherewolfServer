package alessandro.stamera.wherewolfserver.classi.ruoli.guardie;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.CRIMINALI;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGuardiaCorrotta
{

    private static final String NOME = "Guardia corrotta";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        verificaStringa(ruolo.getDescrizione(), "La prima notte riconosce le altre guardie e in seguito gli altri criminali.");
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(CRIMINALI); }

    @Test public void testGuardiaCorrotta() { assertThat(ruolo.isGuardiaCorrotta()).isTrue(); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "La Guardia Corrotta è il doppiogiochista per definizione. E' una delle guardie, ma il suo scopo è quello di eliminare le eventuali " +
            "altre Guardie prima della fine della partita. Come tutti i Criminali gioca una partita nella partita, dovendo prestare attenzione ai " +
            "Lupi, ma allo stesso tempo cercando di mascherare la propria identità, sopratutto alle altre Guardie sue compagne. Qualora non foste " +
            "l'unico Criminale e ci siano altre Guardie, sarà per voi fondamentale farlo capire ai vostri compagni per salvaguardarli, ma " +
            "sopratutto per dare un bersaglio ad un eventuale Assassino";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

}