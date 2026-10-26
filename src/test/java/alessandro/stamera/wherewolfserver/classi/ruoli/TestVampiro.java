package alessandro.stamera.wherewolfserver.classi.ruoli;

import alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura;
import alessandro.stamera.wherewolfserver.classi.ruoli.classi_generiche.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Aura.NERA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Categoria.CREATURE_OMBRA;
import static alessandro.stamera.wherewolfserver.classi.attributi_ruolo.Fazione.VAMPIRO;
import static alessandro.stamera.wherewolfserver.classi.gestione_partita.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestVampiro
{

    private static final String NOME = "Vampiro";

    private Ruolo ruolo;

    @BeforeEach public void setUp() { ruolo = FACTORY.getRuolo(NOME); }

    @Test public void testNome() { verificaStringa(ruolo.getNome(), NOME); }

    @Test public void testDescrizione()
    {
        String descrizione =
            "La prima notte riconosce il Ghoul. Dalla seconda notte, può indicare un giocatore che viene avvisato. Se è il Cacciatore di vampiri " +
            "o un lupo, il Vampiro viene ucciso. Se è un mistico, non accade nulla. Altrimenti, quel giocatore lo riconosce e diventa una " +
            "progenie vampirica con aura oscura e fazione Vampiro. Inoltre, la prima notte individua la Megera.";
        verificaStringa(ruolo.getDescrizione(), descrizione);
    }

    @Test public void testFazione() { assertThat(ruolo.getFazione()).isEqualTo(VAMPIRO); }

    @Test public void testAmanti() { verificaFalso(ruolo.isAmanti()); }

    @Test public void testCitta() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testCriminale() { verificaFalso(ruolo.isCriminale()); }

    @Test public void testInquisizione() { verificaFalso(ruolo.isInquisizione()); }

    @Test public void testLupo() { verificaFalso(ruolo.isLupo()); }

    @Test public void testNosferatu() { verificaFalso(ruolo.isNosferatu()); }

    @Test public void testVampiro() { verificaVero(ruolo.isVampiro()); }

    @Test public void testVillaggio() { verificaFalso(ruolo.isVillaggio()); }

    @Test public void testPosseduto() { verificaFalso(ruolo.isPosseduto()); }

    @Test public void testLune() { assertThat(ruolo.getLune()).isEqualTo(2); }

    @Test public void testCategoria() { assertThat(ruolo.getCategoria()).isEqualTo(CREATURE_OMBRA); }

    @Test public void testCreaturaOmbra() { verificaVero(ruolo.isCreaturaOmbra()); }

    @Test public void testUomini() { verificaFalso(ruolo.isCategoriaUomini()); }

    @Test public void testControlloMedium() { verificaAuraNera(ruolo.controlloMedium()); }

    @Test public void testMistico() { verificaFalso(ruolo.isMistico()); }

    @Test public void testConsigliGioco()
    {
        String messaggio =
            "Di notte non siete potenti quanto i Lupi e dovete temere i loro attacchi; di giorno, non siete influenti quanto il villaggio e " +
            "dovete temere il rogo. I vostri poteri vi rendono comunque temibile in entrambi i momenti del gioco. Scegliete bene le vittime, " +
            "aspettando anche un turno in più, se necessario, e non cercate di attaccare ogni turno. Scoprirete che, se ben gestiti, anche solo " +
            "un paio di Non Morti sono sufficienti per assicurarsi la vittoria, anche qualora voi doveste essere uccisi nel frattempo. Ricordate " +
            "che i Non Morti non si conoscono fra di loro. Sarete voi, scegliendo accuratamente le parole, a far loro capire chi devono votare " +
            "e di chi devono fidarsi";
        verificaStringa(ruolo.getConsigli(), messaggio);
    }

    private void verificaAuraNera(Aura aura) { assertThat(aura).isEqualTo(NERA); }

    private void verificaStringa(String valore, String risultato) { assertThat(valore).isEqualTo(risultato); }

    private void verificaVero(boolean valore) { assertThat(valore).isTrue(); }

    private void verificaFalso(boolean valore) { assertThat(valore).isFalse(); }

}