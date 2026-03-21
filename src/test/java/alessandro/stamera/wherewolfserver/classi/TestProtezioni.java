package alessandro.stamera.wherewolfserver.classi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static alessandro.stamera.wherewolfserver.classi.Partita.FACTORY;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestProtezioni
{

    private Protezioni protezioni;

    @BeforeEach public void setUp() { protezioni = new Protezioni(); }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo" })
    public void testCreatureOmbra(String nome)
    {
        protezioni.aggiungiProtezioneCreatureOmbra();
        verificaPresenza(nome);
    }

    @ParameterizedTest @CsvSource({ "Capo branco, Lupo del branco, Lupo solitario, Lupo reietto, Giovane lupo" })
    public void testCappuccettoRosso(String nome)
    {
        protezioni.aggiungiProtezioneLupi();
        verificaPresenza(nome);
    }

    @Test public void testPerdiProtezioni()
    {
        protezioni.perdiProtezioni();
        for(int i = 0; i < FACTORY.getNumeroRuoli(); i++) verificaAssenza(getRuolo(FACTORY.getNome(i)));
    }

    private void verificaAssenza(Ruolo ruolo) { assertThat(isPresente(ruolo)).isFalse(); }

    private void verificaPresenza(String nome) { verificaPresenza(getRuolo(nome)); }

    private Ruolo getRuolo(String nome) { return FACTORY.getRuolo(nome); }

    private void verificaPresenza(Ruolo ruolo) { assertThat(isPresente(ruolo)).isTrue(); }

    private boolean isPresente(Ruolo ruolo) { return protezioni.isPresente(ruolo); }

}