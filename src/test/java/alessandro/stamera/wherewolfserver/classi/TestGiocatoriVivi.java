package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGiocatoriVivi
{

    private static final String[][] ESEMPI_GIOCATORI =
        new String[][] { { "Marco", "Angelo custode" }, { "Giulio", "Pazzo" }, { "Cesare", "Peccatore" }, { "Augusto", "Prete" } };

    private GiocatoriVivi giocatori;

    @BeforeEach public void setUp()
    {
        giocatori = new GiocatoriVivi();
        for(String[] esempio : ESEMPI_GIOCATORI) giocatori.aggiungiGiocatore(esempio[0], FACTORY.getRuolo(esempio[1]));
    }

    @Test public void testBallottaggioPuro()
    {
        int[] numeroVoti = new int[] { 2, 1 };
        for(int i = 0; i < numeroVoti.length; i++) incrementaVoti(i + 1, numeroVoti[i]);
        Ballottaggio ballottaggio = new Ballottaggio();
        verificaNumeroAccusati(ballottaggio, 2);
        String[] soluzioni = new String[] { getNomeGiocatore(2), getNomeGiocatore(1) };
        int numeroGiocatoriBallottaggio = ballottaggio.getNumeroGiocatori();
        verificaNumeroAccusati(ballottaggio, 2);
        for(int i = 0; i < numeroGiocatoriBallottaggio; i++) assertThat(ballottaggio.getNomeGiocatore(i)).isEqualTo(soluzioni[i]);
    }

    @Test public void testUnanimita()
    {
        incrementaVoti(0, 3);
        Giocatori ballottaggio = getBallottaggio();
        verificaNumeroAccusati(ballottaggio, 1);
        assertThat(ballottaggio.getNomeGiocatore(0)).isEqualTo(getNomeGiocatore(0));
    }

    @Test public void testPareggioPrimoPosto()
    {
        for(int i = 1; i < ESEMPI_GIOCATORI.length; i++) incrementaVoti(i, 1);
        verificaNumeroAccusati(getBallottaggio(), 3);
    }

    private void incrementaVoti(int posizione, int voti) { giocatori.incrementaVoti(getNomeGiocatore(posizione), voti); }

    private String getNomeGiocatore(int posizione) { return ESEMPI_GIOCATORI[posizione][0]; }

    private void verificaNumeroAccusati(Giocatori ballottaggio, int numeroAccusati)
    {
        assertThat(ballottaggio.getNumeroGiocatori()).isEqualTo(numeroAccusati);
    }

    private Giocatori getBallottaggio() { return giocatori.getBallottaggio(); }

}